import { useNavigate, useParams } from "react-router-dom";
import Footer from "../layout/Footer";
import Header from "../layout/Header";
import { addToCardDetail, productDetail } from "../../service/Cart";
import { useEffect, useState } from "react";
import swal from "sweetalert2";
import { infoAppUserByJwtToken } from "../../service/Account";
import { useSelector, useDispatch } from "react-redux";
import { getCart } from "../reducer/CartReducer";
import { Link } from "react-router-dom";
import Swal from "sweetalert2";
const currency = (number) => {
    const roundedNumber = Math.floor(number);
    const formattedNumber = roundedNumber.toLocaleString("vi", {
        style: "currency",
        currency: "VND",
    });
    return formattedNumber;
};
export default function CartDetail() {
    const [detailProduct, setDetailProduct] = useState();
    const param = useParams();
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const cart = useSelector((state) => state.cartReducer);
    const addToCards = async () => {
        let quantity = document.getElementById("inputQuantity");
        console.log(quantity.value);
        const result = infoAppUserByJwtToken();
        if (!result) {
            Swal.fire("Vui lòng đăng nhập tài khoản!", "", "warning");
            navigate("/home/login")
        }
        else {
            try {
                const res = await addToCardDetail(param.id, result.sub, quantity.value);
                dispatch(getCart(result.sub));
                swal.fire("Thêm mới giỏ hàng thành công!")
            } catch (e) {
                swal.fire(
                    "Sản phẩm vượt quá số lượng cho phép!",
                    "Số sản phẩm lượng còn lại là: " + detailProduct.quantity,
                    "warning"
                );
            }
        }

    }

    const findProductsById = async () => {
        const res = await productDetail(param.id);
        setDetailProduct(res.data);
    };

    useEffect(() => {
        findProductsById();
    }, [param])

    if (detailProduct == null) {
        return null;
    }
    return (
        <>
            <Header />
            <div>
                <section className="py-5">
                    <div className="container px-4 px-lg-5 my-5">
                        <div className="row gx-4 gx-lg-5 align-items-center">
                            <div className="col-md-6"><img className="card-img-top mb-5 mb-md-0" src={detailProduct.img} alt="..." /></div>
                            <div className="col-md-6">
                                <div className="small mb-1">Mã: {detailProduct.code}</div>
                                <h1 className="display-5 fw-bolder">{detailProduct.name}</h1>
                                <div className="fs-5 mb-5">
                                    <span>{currency(detailProduct.price)}</span>
                                </div>
                                {detailProduct.weight && (
                                    <p className="lead">Trọng lượng: {detailProduct.weight} g</p>
                                )}
                                {detailProduct.handleSize && (
                                    <p className="lead">Handle size: {detailProduct.handleSize}</p>
                                )}
                                {detailProduct.racketType && (
                                    <p className="lead">Kiểu vợt: {detailProduct.racketType}</p>
                                )}
                                {detailProduct.bridgeSpeed && (
                                    <p className="lead">Tốc độ cầu: {detailProduct.bridgeSpeed}</p>
                                )}
                                {detailProduct.material && (
                                    <p className="lead">Chất liệu: {detailProduct.material}</p>
                                )}
                                {detailProduct.note && (
                                    <p className="lead">Ghi chú: {detailProduct.note}</p>
                                )}
                                <div className="d-flex">
                                    <input className="form-control text-center me-3" id="inputQuantity" type="num" defaultValue={1} style={{ maxWidth: '3rem' }} />
                                    <button className="btn btn-outline-dark flex-shrink-0" type="button" onClick={() => addToCards()}>
                                        {/* <i className="bi-cart-fill me-1" /> */}
                                        Mua sản phẩm
                                    </button>
                                    <div className="d-flex align-items-center ml-3">
                                        <h6 className="mb-0"><Link to={`/`} className="text-body"><i className="fas fa-long-arrow-alt-left me-2" />Quay lại cửa hàng</Link></h6>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </section>

            </div>
            <Footer />
        </>
    )
}