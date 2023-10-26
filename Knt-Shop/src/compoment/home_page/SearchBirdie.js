import Header from "../layout/Header";
import Footer from "../layout/Footer";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { addToCard, checkProductInCart, findAllCart } from "../../service/Cart";
import { infoAppUserByJwtToken } from "../../service/Account";
import { getCart } from "../reducer/CartReducer";

import {
    AiOutlineDoubleLeft,
    AiOutlineDoubleRight,
} from "react-icons/ai";
import Swal from "sweetalert2";
import { useSelector, useDispatch } from "react-redux";
import { getProductByName } from "../../service/Product";
import axios from "axios";

const currency = (number) => {
    const roundedNumber = Math.floor(number);
    const formattedNumber = roundedNumber.toLocaleString("vi", {
        style: "currency",
        currency: "VND",
    });
    return formattedNumber;
};
function SearchBirdie() {
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPage, setTotalPage] = useState(0);
    const navigate = useNavigate();
    const params = useParams();
    const dispatch = useDispatch();
    const nextPage = () => {
        if (page + 1 < totalPage) {
            setPage((prev) => prev + 1)
        }
    }


    const getAllProductByName = async () => {
        const res = await axios.get(`http://localhost:8080/product-birdie`)
        setTotalPage(res.data.totalPages);

        console.log(res.data.content);
        setProducts(res.data.content);
    }

    useEffect(() => {
        getAllProductByName();
    }, [params.keyword, page])

    const addToCards = async (p) => {
        const result = infoAppUserByJwtToken();
        if (!result) {
            Swal.fire("Vui lòng đăng nhập tài khoản!", "", "warning");
            navigate("/home/login")
        }
        else {
            const res = await checkProductInCart(p.id, result.sub);
            if (res.status == 200) {
                await addToCard(p.id, 1, result.sub);
                dispatch(getCart(result.sub));
                // console.log(cart);
                Swal.fire("Sản phẩm đã được thêm trong giỏ hàng!")
            }
            else {
                Swal.fire("Sản phẩm đã có trong giỏ hàng!")
            }
        }
    }

    const prevPage = () => {
        if (page > 0) {
            setPage((prev) => prev - 1)
        }
    }
    return (
        <>
            <div>
                <Header />
                <div className="container px-4 px-lg-5 mt-5">
                    <div className="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                        {products?.map((p, index) => (
                            <div className="col mb-5">
                                <div className="card h-100">
                                    <img className="card-img-top" src={p.img} alt="..." />
                                    <div className="card-body p-4">
                                        <div className="text-center">
                                            <h6 className="fw-bolder">{p.name}</h6>
                                            {currency(p.price)}
                                        </div>
                                    </div>
                                    <div className="card-footer p-4 pt-0 border-top-0 bg-transparent d-flex justify-content-between align-items-center">
                                        <div className="text-center">
                                            <Link className="btn btn-outline-dark mt-auto" to={`/home/cartDetail/${p.id}`}>
                                                Xem chi tiết
                                            </Link>
                                        </div>
                                        <div className="text-center">
                                            <button className="btn btn-outline-dark mt-auto" onClick={() => addToCards(p)}>Mua hàng</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
                <div className="px-5 py-3 bg-white border-t flex flex-col xs:flex-row items-center xs:justify-between">
                    <div className="justify-content-center d-flex">
                        <button className="btn btn-outline-dark" style={{ margin: 5 }} onClick={() => prevPage()}>
                            <AiOutlineDoubleLeft />
                        </button>
                        <div
                            className="text-sm py-2 px-4 border border-1 border-dark"
                            style={{

                                margin: 5,
                                borderRadius: 5,
                            }}
                        >
                            {page + 1}/{totalPage}
                        </div>
                        <button className="btn btn-outline-dark" style={{ margin: 5 }} onClick={() => nextPage()}>
                            <AiOutlineDoubleRight />
                        </button>
                        <div
                            className="rounded-lg"
                            style={{
                                background: "#0d6efd",
                                color: "black",
                                margin: 5,
                                borderRadius: 5,
                            }}
                        />
                    </div>
                </div>
                <Footer />
            </div>
        </>
    )
}
export default SearchBirdie;