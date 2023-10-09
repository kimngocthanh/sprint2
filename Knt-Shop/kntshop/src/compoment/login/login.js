import { Field } from "formik";
import { Form, Link } from "react-router-dom";
import Header from "../layout/Header";
import Footer from "../layout/Footer";

function Login() {
    return (
        <>
            <section className="vh-100 mt-5">
                <div className="container-fluid h-custom">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-md-9 col-lg-6 col-xl-5">
                            <img src="https://img.lovepik.com/free-png/20210926/lovepik-athletes-playing-badminton-png-image_401380502_wh1200.png" className="img-fluid" style={{ height: '75%', width: '75%' }} alt="Sample image" />
                        </div>
                        <div className="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                            <form>
                                <div className="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                                
                                    <p className="lead fw-normal mb-0 me-3 fw-bold">Đăng nhập</p>
                                </div>
                                <div className="divider d-flex align-items-center my-4">
                                </div>
                                <div className="form-outline mb-4">
                                    <label>Tài khoản</label>
                                    <input type="email" id="form3Example3" className="form-control form-control-lg" placeholder="Nhập tài khoản ..." />
                                </div>
                                <div className="form-outline mb-3">
                                    <label>Mật khẩu</label>
                                    <input type="password" id="form3Example4" className="form-control form-control-lg" placeholder="Nhập mật khẩu ..." />
                                </div>
                                <div className="d-flex justify-content-between align-items-center">
                                    <div className="form-check mb-0">
                                        <input className="form-check-input me-2" type="checkbox" defaultValue id="form2Example3" />
                                        <label className="form-check-label" htmlFor="form2Example3">
                                            Ghi nhớ đăng nhập
                                        </label>
                                    </div>
                                    <a href="#!" className="text-body">Quên mật khẩu?</a>
                                </div>
                                <div className="text-center text-lg-start mt-4 pt-2">
                                    <button type="button" className="btn btn-primary btn-lg" style={{ paddingLeft: '2.5rem', paddingRight: '2.5rem' }}>Đăng nhập</button>
                                    <p className="small fw-bold mt-2 pt-1 mb-0">Bạn chưa có tài khoản? <a href="#!" className="link-danger">Đăng ký</a></p>
                                </div>
                            </form>
                            
                        </div>

                    </div>
                </div>
            </section>
        </>
    )
}

export default Login;