import {createRouter, createWebHistory} from 'vue-router/dist/vue-router.esm-bundler'
import LoginComponent from "@/components/auth/LoginComponent";
import RegisterComponent from "@/components/auth/RegisterComponent";
import LogoutComponent from "@/components/auth/LogoutComponent";
import CruiseShipsPage from "@/pages/CruiseShipsPage.vue";
import MyOrdersPage from "@/pages/MyOrdersPage.vue";
import CruiseShipDetailPage from "@/components/CruiseShipComponents/CruiseShipDetailPage.vue";
import UserOrderRentCruiseShipComponent from "@/components/UserOrderComponents/UserOrderRentCruiseShipComponent.vue";

const routes = [
    {
        path: '/',
        component: CruiseShipsPage,
    },
    {
        path: '/login',
        component: LoginComponent,
    },
    {
        path: '/register',
        component: RegisterComponent,
    },
    {
        path: '/logout',
        component: LogoutComponent,
    },
    {
        path: '/cruise-ships',
        component: CruiseShipsPage,
    },
    {
        name: 'user-order-list',
        path: '/user-orders',
        component: MyOrdersPage,
    },
    {
        name: 'cruise-ship-detail',
        path: '/cruise-ships/:id',
        component: CruiseShipDetailPage,
        props: route => ({cruiseShipId: route.params.id}),
    },    {
        name: 'user-order-rent-cruise-ship-by-id',
        path: '/user-order-rent/:id',
        component: UserOrderRentCruiseShipComponent,
        props: route => ({cruiseShipId: route.params.id}),
    },

]

export default () => createRouter({
    history: createWebHistory(),
    routes: routes
})