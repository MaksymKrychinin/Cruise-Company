import {createRouter, createWebHistory} from 'vue-router/dist/vue-router.esm-bundler'
import Home from '@/pages/HomePage.vue'
import LoginComponent from "@/components/auth/LoginComponent";
import RegisterComponent from "@/components/auth/RegisterComponent";
import LogoutComponent from "@/components/auth/LogoutComponent";

const routes = [
    {
        path: '/',
        component: Home
    },
    {
        path: '/login',
        component: LoginComponent
    },
    {
        path: '/register',
        component: RegisterComponent
    },
    {
        path: '/logout',
        component: LogoutComponent
    }
]

export default () => createRouter({
    history: createWebHistory(),
    routes: routes
})