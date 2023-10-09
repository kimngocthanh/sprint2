import { Link } from "react-router-dom";
function Header() {
    return (
        <>
            {/* <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container px-4 px-lg-5">
                    <Link className="navbar-brand" to={`/`}>KNT Shop</Link>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span className="navbar-toggler-icon" /></button>
                    <div className="collapse navbar-collapse" >
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                            <li className="nav-item"><Link className="nav-link active" aria-current="page" to={`/`}>Trang chủ</Link></li>
                            <li className="nav-item"><a className="nav-link" href="#!">Về chúng tôi</a></li>
                            <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Danh mục</a>
                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a className="dropdown-item" href="#!">Vợt cầu lông</a></li>
                                    <li><a className="dropdown-item" href="#!">Quả cầu lông</a></li>
                                </ul>
                            </li>
                        </ul>
                        <form className="d-flex">
                            <Link className="btn btn-outline-dark" to={`/home/cart`}>
                                <i className="bi-cart-fill me-1" />
                                Giỏ hàng
                                <span className="badge bg-dark text-white ms-1 rounded-pill">0</span>
                            </Link>
                        </form>
                    </div>
                    <div>
                        
                    </div>
                </div>
            </nav> */}
            <header className="p-3 mb-3 border-bottom bg-light">
                <div className="container">
                    <div className="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                        <a href="/" className="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                            <svg className="bi me-2" width={40} height={32} role="img" aria-label="Bootstrap"><use xlinkHref="#bootstrap" /></svg>
                        </a>
                        <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                            <li><Link to={`/`} className="nav-link px-2 link-dark fw-bold">KNT-Shop</Link></li>
                            <li><Link to={`/`} className="nav-link px-2 link-dark">Trang chủ</Link></li>
                            <li><a href="#" className="nav-link px-2 link-dark">Về chúng tôi</a></li>
                            <li><a href="#" className="nav-link px-2 link-dark">Danh mục</a></li>
                        </ul>
                        <form className="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                            <input type="search" className="form-control" placeholder="Tìm kiếm sản phẩm..." aria-label="Search" />
                        </form>
                        <form className="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                            <Link className="btn btn-outline-dark" to={`/home/cart`}>
                                Giỏ hàng
                                <span className="badge bg-black text-white ms-1 rounded-pill">3</span>
                            </Link>
                        </form>
                        <div className="dropdown text-end">
                            <a href="#" className="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="https://antimatter.vn/wp-content/uploads/2022/11/anh-avatar-trang-fb-mac-dinh.jpg" alt="mdo" width={32} height={32} className="rounded-circle" />
                            </a>

                            <ul className="dropdown-menu text-small" aria-labelledby="dropdownUser1" style={{}}>
                                <li><Link to={`/home/login`} className="dropdown-item" >Đăng nhập</Link></li>
                                <li><a className="dropdown-item" href="#">Đăng xuất</a></li>
                                <li><a className="dropdown-item" href="#">Thông tin</a></li>

                            </ul>
                        </div>
                    </div>
                </div>
            </header>

        </>
    )
}
export default Header;