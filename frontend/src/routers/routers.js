import { createRouter, createWebHistory } from "vue-router";
import home from "../components/Home.vue";
import register from "../components/RegisterUser.vue";
import login from "../components/LoginUser.vue";
import order from "../components/Order.vue";
import addProduct from "../components/AddProduct.vue";
import confirmOrder from "../components/ConfirmOrder.vue";
import Querry1 from "../components/Querry1.vue";
import Querry2 from "../components/Querry2.vue";
import Querry3 from "../components/Querry3.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: home,
  },
  {
    path: "/registerUser",
    name: "register",
    component: register,
  },
  {
    path: "/login",
    name: "login",
    component: login,
  },
  {
    path: "/order",
    name: "order",
    component: order,
  },
  {
    path: "/addProduct",
    name: "AddProduct",
    component: addProduct,
  },
  ,
  {
    path: "/confirmOrder",
    name: "ConfirmOrder",
    component: confirmOrder,
  },
  {
    path: "/Querry1",
    name: "Querry1",
    component: Querry1,
  },
  {
    path: "/Querry2",
    name: "Querry2",
    component: Querry2,
  },
  {
    path: "/Querry3",
    name: "Querry3",
    component: Querry3,
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
  linkActiveClass: "active-link",
});

export default router;
