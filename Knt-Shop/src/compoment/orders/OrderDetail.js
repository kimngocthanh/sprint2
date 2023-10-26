import { useEffect, useState } from "react";
import Footer from "../layout/Footer";
import Header from "../layout/Header";
import { infoAppUserByJwtToken } from "../../service/Account";
import { findAllOrdersDetail } from "../../service/Cart";
import { Link, useParams } from "react-router-dom";
import axios from "axios";

const currency = (number) => {
    const roundedNumber = Math.floor(number);
    const formattedNumber = roundedNumber.toLocaleString("vi", {
        style: "currency",
        currency: "VND",
    });
    return formattedNumber;
};
function OrderDetail() {
    const [orders, setOrders] = useState([]);
    const params = useParams();
    const getAllOrder = async () => {
        const result = infoAppUserByJwtToken();
        const res = await findAllOrdersDetail(result.sub, params.code);
        console.log(res);
        setOrders(res.data);
    }

    useEffect(() => {
        getAllOrder();
    }, [])
    return (
        <>
            <Header />
            <div className="p-5">
                <div className="d-flex justify-content-between align-items-center mb-5">
                    <h1 className="fw-bold mb-0 text-black">KNT shop</h1>
                </div>
                <div className="row mb-4 d-flex justify-content-between align-items-center">
                    {
                        orders.map((p, index) => {
                            return (
                                <>

                                    <div className="row">
                                        <hr className="my-4" />
                                        <div className="col-md-1 col-lg-1 col-xl-1">
                                            <div>{index + 1}</div>
                                        </div>
                                        <div className="col-md-2 col-lg-2 col-xl-2">
                                            <div>{p.name}</div>
                                        </div>
                                        <div className="col-md-2 col-lg-2 col-xl-1">
                                            <img src={p.img} className="img-fluid rounded-3" alt="" />
                                        </div>
                                        <div className="col-md-3 col-lg-3 col-xl-3">
                                            <div>{p.dateTime}</div>
                                        </div>
                                        <div className="col-md-2 col-lg-2 col-xl-2">
                                            <div>{p.quantity}</div>
                                        </div>
                                        <div className="col-md-2 col-lg-2 col-xl-2">
                                            <div>{currency(p.totalPrice)}</div>
                                        </div>
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
            <Footer />
        </>
    )
}
export default OrderDetail;