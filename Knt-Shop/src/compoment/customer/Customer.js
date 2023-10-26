import { ErrorMessage, Field, Form, Formik } from "formik";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { createCustomer, getCustomerDetail } from "../../service/Customer";
import { infoAppUserByJwtToken } from "../../service/Account";
import Swal from "sweetalert2";
function Customer() {
    const [customer, setCustomer] = useState()
    const navigate = useNavigate();
    const getCustomer = async () => {
        const result = infoAppUserByJwtToken();
        if (result != null) {
            const res = await getCustomerDetail(result.sub);
            if (res != null) {
                setCustomer(res.data)
                console.log(customer);
            }
        }

    }
    const updateCustomer = async (value) => {
        try {
            const result = infoAppUserByJwtToken();
            await createCustomer(value, result.sub);
            Swal.fire(
                "Cập nhật thông tin thành công!"
            )
            navigate("/")
        } catch (e) {
            console.log(e);
        }
    }



    useEffect(() => {
        getCustomer();
    }, [])
    if (!customer) {
        return null;
    }
    return (
        <>
            <Formik
                initialValues={{
                    id: customer?.id,
                    name: customer?.name,
                    phone: customer?.phone,
                    dob: customer?.dob,
                    address: customer?.address
                }}
                onSubmit={(value) => {
                    updateCustomer(value);
                }}
            >
                <div className=" d-flex justify-content-center align-items-center" style={{height: "100vh"}}>
                     <fieldset className="border border-dark rounded-3 p-3 w-50" style={{ backgroundColor: '#f8f9fa' }}>
                    <legend className="float-none w-auto px-3">Thông tin khách hàng</legend>
                    <Form>
                        <div className="mb-3 row">
                            <label className="col-sm-3 col-form-label" id="label-input" >Tên khách hàng</label>
                            <div className="col-sm-9">
                                <Field type="text" className="form-control" name='name' />
                                <div style={{ height: '15px' }}>
                                    <ErrorMessage name="name" component="small" style={{ color: 'red' }} />
                                </div>
                            </div>
                        </div>
                        <div className="mb-3 row">
                            <label className="col-sm-3 col-form-label" id="label-input">Ngày sinh</label>
                            <div className="col-sm-9">
                                <Field type="date" className="form-control" name='dob' />
                                <div style={{ height: '15px' }}>
                                    <ErrorMessage name="dob" component="small" style={{ color: 'red' }} />
                                </div>
                            </div>
                        </div>

                        <div className="mb-3 row">
                            <label className="col-sm-3 col-form-label" id="label-input" >Số điện thoại</label>
                            <div className="col-sm-9">
                                <Field type="text" className="form-control" name='phone' />
                                <div style={{ height: '15px' }}>
                                    <ErrorMessage name="phone" component="small" style={{ color: 'red' }} />
                                </div>
                            </div>
                        </div>
                        <div className="mb-3 row">
                            <label className="col-sm-3 col-form-label" id="label-input" >Địa chỉ</label>
                            <div className="col-sm-9">
                                <Field type="text" className="form-control" name='address' />
                                <div style={{ height: '15px' }}>
                                    <ErrorMessage name="address" component="small" style={{ color: 'red' }} />
                                </div>
                            </div>
                        </div>
                        <div className="d-flex justify-content-end w-100 gap-2">
                            <button type="submit" className=" btn btn-outline-dark" >
                                Cập nhật</button>
                            <Link to='/' className="btn btn-outline-dark">Trở về</Link>
                        </div>
                    </Form>
                </fieldset></div>
               
            </Formik>
        </>
    )
}
export default Customer;