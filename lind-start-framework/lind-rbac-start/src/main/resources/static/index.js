//export和export default都是导出，export default只能有一个，在使用import导入时export的需要加{}，而export default的不需要加{}
export const  getRequest=(url, params) =>{
    return axios({
        method: 'get',
        url: url,
        params: params
    });
}