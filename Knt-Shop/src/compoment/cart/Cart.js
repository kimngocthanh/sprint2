import { useState, useEffect, useRef } from "react";
import Footer from "../layout/Footer";
import Header from "../layout/Header";
import {checkQuantity, deleteCard, deleteOneToCard, findAllCart, updateToCard } from "../../service/Cart";
import { infoAppUserByJwtToken } from "../../service/Account";
import { Link, useNavigate } from "react-router-dom";
import swal from "sweetalert2";
import Paypal from "./Paypal";

const currency = (number) => {
    const roundedNumber = Math.floor(number);
    const formattedNumber = roundedNumber.toLocaleString("vi", {
        style: "currency",
        currency: "VND",
    });
    return formattedNumber;
};

function Cart() {
    const [carts, setCarts] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);
    const [selectedProducts, setSelectedProducts] = useState([]);
    const paypal = useRef();
    const [checkout, setCheckOut] = useState(false);
    const navigate = useNavigate();


    const increaseQuantity = async (p) => {
        let quantity = document.getElementById("input-quantity" + p.id);
        try {
            const res = await checkQuantity(p.id, parseInt(quantity.value) + 1)
            if (quantity.value < 99) {
                quantity.value = parseInt(quantity.value) + 1;
            } else {
                quantity.value = 99;
            }
            const result = infoAppUserByJwtToken();
            await updateToCard(p.id, 1, result.sub);
            finAllCartDetail();
        } catch (e) {
            swal.fire(
                "Sản phẩm vượt quá số lượng cho phép!",
                "Số sản phẩm lượng còn lại là: " + p.quantity,
                "warning"
            );
        }

    };

    const handleCheckboxChange = (product) => {
        if (selectedProducts.find((el) =>
            product.id === el.id
        )) {
            setSelectedProducts(selectedProducts.filter((el) => el.id != product.id))
        } else {
            setSelectedProducts([...selectedProducts, product])
        }
    }

    // Cập nhật state của danh sách sản phẩm
    //  setSelectedProducts(carts.filter((product) => product.selected))
    // const selectedProducts = carts.filter((product) => product.selected);

    


    const decreaseQuantity = async (p) => {
        let quantity = document.getElementById("input-quantity" + p.id);
        try {
            if (quantity.value <= 1) {

                swal
                    .fire({
                        title: "Bạn có muốn xoá sản phẩm này khỏi giỏ hàng?",
                        text: p.name,
                        icon: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#3085d6",
                        cancelButtonColor: "#d33",
                        confirmButtonText: "Đồng ý",
                        cancelButtonText: "Huỷ",
                    })
                    .then(async (willDelete) => {
                        if (willDelete.isConfirmed) {
                            const result = infoAppUserByJwtToken();
                            await deleteCard(p.id, result.sub);
                            swal.fire("Xoá sản phẩm thành công!", "", "success");
                            finAllCartDetail()
                        }
                    })
            }
            if (quantity.value > 1) {
                quantity.value = parseInt(quantity.value) - 1;
                const result = infoAppUserByJwtToken();
                await deleteOneToCard(p.id, 1, result.sub)
                finAllCartDetail();
            }

        }
        catch (e) {
            console.log(e);
        }
    };

    const addToOrder =async () => {
        console.log(selectedProducts);
        setCheckOut(true);
        // await addToOrders();
        navigate("/home/cart")

    }

    const finAllCartDetail = async () => {
        const result = infoAppUserByJwtToken();
        const res = await findAllCart(result.sub);
        setCarts(res.data);

    }

    useEffect(() => {
        finAllCartDetail();
        const total = selectedProducts.reduce((acc, product) => {
            return acc + product.price * product.quantity;
        }, 0);

        // Cập nhật state của tổng tiền
        setTotalPrice(total);
        
    }, [selectedProducts]);

    // useEffect(() => {
    // }, []);



    return (
        <>
            <Header />
            <div className="h-100 h-custom" style={{ backgroundColor: '#FFFFFF' }}>
                <div className="container py-5 h-100">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-12 ">
                            <div className="card card-registration card-registration-2" style={{ borderRadius: 15 }}>
                                <div className="card-body p-0">

                                    <div className="row g-0  position-relative">

                                        <div className="col-lg-8">

                                            <div className="p-5">
                                                <div className="d-flex justify-content-between align-items-center mb-5">
                                                    <h1 className="fw-bold mb-0 text-black">KNT shop</h1>
                                                    <h6 className="mb-0 text-muted">{carts.length} sản phẩm</h6>
                                                </div>


                                                <div className="row mb-4 d-flex justify-content-between align-items-center">
                                                    {
                                                        carts.map((p, index) => {
                                                            return (
                                                                <>

                                                                    <hr className="my-4" />
                                                                    <div className="col-md-1 col-lg-1 col-xl-1">
                                                                        <input type="checkbox" onChange={() => handleCheckboxChange(p)} />
                                                                    </div>
                                                                    <div className="col-md-1 col-lg-1 col-xl-1">
                                                                        <img src={p.img} className="img-fluid rounded-3" alt="" />
                                                                    </div>
                                                                    <div className="col-md-3 col-lg-3 col-xl-3">
                                                                        <div>{p.name}</div>
                                                                    </div>
                                                                    <div className="col-md-3 col-lg-3 col-xl-2 d-flex">
                                                                        <button type="button" className="d-flex justify-content-center btn-plus" style={{ border: "1px white" }} onClick={() => decreaseQuantity(p)}>-</button>
                                                                        <input
                                                                            id={`input-quantity${p.id}`}
                                                                            min="1"
                                                                            max="99"
                                                                            name="quantity"
                                                                            defaultValue={p.quantity}
                                                                            type="number"
                                                                            className="form-control form-control-sm text-center"
                                                                            disabled
                                                                        />
                                                                        <button type="button" className="d-flex justify-content-center btn-plus" style={{ border: "1px white" }} onClick={() => increaseQuantity(p)}>+</button>
                                                                    </div>
                                                                    <div className="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                                                        <div className="mb-0">{currency(p.price * p.quantity)}</div>
                                                                    </div>
                                                                    <div className="col-md-1 col-lg-1 col-xl-1 text-end">
                                                                        <a href="#!" className="text-muted"><i className="fas fa-times" /></a>
                                                                    </div>
                                                                </>

                                                            )
                                                        })


                                                    }
                                                </div>
                                                <hr className="my-4" />
                                                <div className="pt-5">
                                                    <h6 className="mb-0"><Link to={`/`} className="text-body"><i className="fas fa-long-arrow-alt-left me-2" />Quay lại cửa hàng</Link></h6>
                                                </div>
                                            </div>


                                        </div>
                                        <div className="col-lg-4 bg-grey position-sticky top-0 h-100">
                                            <div className="p-5">
                                                <h3 className="fw-bold mb-5 mt-2 pt-1">KNT-Shop</h3>
                                                <hr className="my-4" />
                                                <div className="d-flex justify-content-between mb-4">
                                                    <h5 className="text-uppercase">{selectedProducts.length} sản phẩm</h5>
                                                    <h5>{currency(totalPrice)}</h5>
                                                </div>

                                                <hr className="my-4" />
                                                <div className="d-flex justify-content-between mb-5">
                                                    <h5 className="text-uppercase">Tổng tiền</h5>
                                                    <h5>{currency(totalPrice)}</h5>
                                                </div>
                                                {/* <button type="button" className="btn btn-dark btn-block btn-lg" data-mdb-ripple-color="dark" ref={paypal} onClick={() => addToOrdes()}>Thanh toán </button> */}
                                                <div >
                                                    {checkout ? (
                                                        <Paypal propData1={totalPrice} propData2={selectedProducts}/>
                                                    ) : (
                                                        <button type="button" className="btn btn-dark btn-block btn-lg" data-mdb-ripple-color="dark"
                                                            onClick={() => {                                                                
                                                                addToOrder();
                                                            }}
                                                        >
                                                            Thanh toán
                                                        </button>
                                                    )}
                                                </div>
                                                {/* <button type="button" className="btn btn-dark btn-block btn-lg" data-mdb-ripple-color="dark" ref={paypal} onClick={() => addToOrdes()}>Thanh toán </button> */}
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div >
            <Footer />
        </>
    )
}

export default Cart;