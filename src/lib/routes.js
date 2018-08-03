import Home from "../view/Home"
import FileDetail from "../view/FileDetail"
const Foo = {template: '<div>foo</div>'};
const Bar = {template: '<div>bar</div>'};

// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
export default  [
    {path: '/', component: Home},
    {path: '/detail/:type/:path', component: FileDetail, name: "detail"},
    {path: '/foo', component: Foo},
    {path: '/bar', component: Bar}
];