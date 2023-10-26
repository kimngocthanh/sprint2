import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { infoAppUserByJwtToken } from "../../service/Account";
import Swal from "sweetalert2";
import { useDispatch, useSelector } from "react-redux";
import { getCart } from "../reducer/CartReducer";
// import { CiSearch } from "react-icons/ci";
import { BiSearchAlt } from 'react-icons/bi'
const Header = ({ inputSearch, onInputChange }) => {
    const [username, setUsername] = useState(null);
    const [JwtToken, setJwtToken] = useState(localStorage.getItem("JWT"));
    const carts = useSelector((state) => state.cartReducer);
    const dispatch = useDispatch();
    const [keyword, setKeyword] = useState("");

    // console.log(carts);
    const navigate = useNavigate();
    const getUsername = () => {

        const res = infoAppUserByJwtToken();
        if (res != null) {
            setUsername(res.sub);
            dispatch(getCart(res.sub))
        }
    }

    const handleLogOut = () => {
        localStorage.removeItem("JWT");
        setJwtToken(undefined);
        setUsername(undefined);
        navigate("/");
        Swal.fire({
            title: "Đăng xuất thành công",
            icon: "success",
        });
        navigate("/");
        window.location.reload();
    };

    const handleInputChange = (event) => {
        setKeyword(event.target.value);
        // console.log(keyword);
    };

    const searchMedicines = (keyword) => {
        console.log(keyword);
        // if(keyword=null){
        //     navigate(`/`);
        // }
        navigate(`/home/search/${keyword}`);
    };

    const handleSearch = (event) => {
        event.preventDefault();
        searchMedicines(keyword);
    };


    useEffect(() => {
        getUsername();
    }, [])

    // if(username===undefined){
    //     return null;
    // }


    return (
        <>
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
                            <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                                <li className="nav-item dropdown">
                                    <a className="nav-link dropdown-toggle text-dark" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        Danh mục
                                    </a>
                                    <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <li><Link className="dropdown-item text-dark" to={`/home/product-racket`}>Vợt cầu lông</Link></li>
                                        <li><Link className="dropdown-item text-dark" to={`/home/product-birdie`}>Quả cầu lông</Link></li>
                                    </ul>
                                </li>
                            </ul>
                        </ul>
                        <div className="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3 d-flex justify-content-center">
                            <input type="search" id="form-input-home" className="form-control" value={inputSearch}
                                placeholder="Tìm kiếm sản phẩm..." onChange={(event) => {
                                    handleInputChange(event);
                                    // onInputChange(event);
                                }} aria-label="Search" />
                            <button type="submit" className="btn btn-outline-dark mx-1" onClick={(e) => handleSearch(e)}>
                                < BiSearchAlt />
                            </button>
                        </div>
                        <form className="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                            <Link className="btn btn-outline-dark" to={`/home/cart`}>
                                Giỏ hàng
                                <span className="badge bg-black text-white ms-1 rounded-pill">{carts.data?.length}</span>
                            </Link>
                        </form>
                        <div className="dropdown text-end ">
                            <div data-bs-toggle="dropdown" className="d-flex justify-content-center">
                                <a href="#" className="d-block link-dark text-decoration-none dropdown-toggle " id="dropdownUser1" aria-expanded="false">
                                    <img src="https://antimatter.vn/wp-content/uploads/2022/11/anh-avatar-trang-fb-mac-dinh.jpg" alt="mdo" width={32} height={32} className="rounded-circle" />
                                </a>
                                {username != null && <p>{username} </p>}
                            </div>
                            <ul className="dropdown-menu text-small" aria-labelledby="dropdownUser1" style={{}}>
                                {JwtToken ? (
                                    <>
                                        <li><Link className="dropdown-item" to={`/home/customer`}>Thông tin</Link></li>
                                        <li><Link className="dropdown-item" to={`/home/orders`}>Lịch sử mua hàng</Link></li>
                                        <li><p className="dropdown-item" onClick={() => handleLogOut()}>Đăng xuất</p></li>
                                    </>
                                ) : <li><Link to={`/home/login`} className="dropdown-item" >Đăng nhập</Link></li>
                                }
                            </ul>
                        </div>
                    </div>
                </div>
            </header>

        </>
    )
}
export default Header;