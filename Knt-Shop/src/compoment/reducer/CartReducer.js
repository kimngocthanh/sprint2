import { findAllCart } from "../../service/Cart";

export const getCart = (appUserId) => async (dispatch) => {
    try {
        const res = await findAllCart(appUserId);
        dispatch({
          type: "GET_ALL_CART",
          payload: res,
        });
      } catch (err) {
        console.log(err);
      }
    };
