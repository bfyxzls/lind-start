//export和export default都是导出，export default只能有一个，在使用import导入时export的需要加{}，而export default的不需要加{}
export function getRequest(url, params) {
    return axios({
        method: 'get',
        url: url,
        params: params,
        headers:{
            'Authorization': "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1MDA3ODcyOCwidXNlciI6IntcImlkXCI6XCIxXCIsXCJ1c2VybmFtZVwiOlwiYWRtaW5cIixcImVtYWlsXCI6XCJhZG1pbkBzaW5hLmNvbVwiLFwiYXV0aG9yaXRpZXNcIjpbe1wiaWRcIjpcIjFcIixcIm5hbWVcIjpcIueuoeeQhuWRmDFcIixcImJ1dHRvbkdyYW50XCI6MzI3Njd9XX0iLCJpYXQiOjE2NDk4OTg3Mjh9.Sxjy_J2stRw8vXsgoWJtGpQhQNvZYRbzzjoZIETabII"
        }
    });
}
