import { createApp } from "vue";
import App from "./App.vue";
import store from "./store/Store";
import router from "./routers/routers";

createApp(App).use(router).use(store).mount("#app");
