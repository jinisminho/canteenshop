export {addIngredient,
     removeIngredient,
    initIngredients
} from './burgerBuilder'
export {
    purchaseBurger,
    purchaseInit,
    fetchOrder
} from './orderBurger'

export {
    auth,
    logout,
    setAuthRedirectPath,
    authCheckState,
    changePassword,
    authStaff,
} from './auth'

export {
    getCategories,
    deleteCategory,
    updateCategory,
    addCategory,
    getAllCategories
} from './category'

export {
    getProduct,
    updateProduct,
    addProduct,
    updateImage,
} from './product'

export {
    getStatistic

} from './statistic'

export { getStaffs,
     updateStaff,
      addStaff } from "./staff";

export {
  changeCustomerStatus,
  getCustomers,
} from './customerAction'

export {
    getOrders,
    getCancelReason
} from './order'

export {
    getOrdersStaff,
    getCancelReasonStaff,
    cancelOrder,
    changeOrderStatusStaff
} from './orderStaff'

export{
getStaffProfile,
updateStaffProfile
} from './staff_profile'

export{
    getAvailableProduct,
    addOrderProduct,
    removeOrderProduct,
    purchaseProduct,
    dissmissSuccess
    } from './checkout'

export{
    getCustomerOrder
} from './customerOrder'