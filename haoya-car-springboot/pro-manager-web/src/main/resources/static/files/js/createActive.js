(function(global) {
	var helpAge = {
		el: "#helpAgeContent",
		data() {
			return {
				addStoreDialog: false,
				// 添加商品弹框显示状态
				goodsDialog: false,
				activeTab: 'a',
				queryCondition: {
					code: '',
					name: '',
					time: '',
					storeName: '',
					status: ''
				},
				// 搜索结果
				activeStoreList: [{
					bzid: '132456-21',
					storeName: '中环百联1'
				}, {
					bzid: '132456-21',
					storeName: '中环百联2'
				}, {
					bzid: '132456-21',
					storeName: '中环百联3'
				}, {
					bzid: '132456-21',
					storeName: '中环百联4'
				}],
				searchStoreData: [{
					bzid: '132456-21',
					storeName: '搜索中环百联1'
				}, {
					bzid: '132456-21',
					storeName: '搜索中环百联2'
				}, {
					bzid: '132456-21',
					storeName: '搜索中环百联3'
				}, {
					bzid: '132456-21',
					storeName: '搜索中环百联4'
				}],
				createStoreData: {
					storeCode: '',
					type: '联华标超',
					storeName: ''
				},
				activeGoodsData: [{
					code: '132456-21',
					goodsId: '1000024687',
					goodsName: '农夫山泉',
					price: 10.00,
					storeList: [{
						storeCode: '00228',
						storeName: '华联超市田林店',
						basePrice: 15.00,
						status: 1
					}, {
						storeCode: '00228',
						storeName: '华联超市田林店',
						basePrice: 15.00,
						status: 1
					}, {
						storeCode: '00228',
						storeName: '华联超市田林店',
						basePrice: 15.00,
						status: 0
					}]
				}, {
					code: '132456-21',
					goodsId: '1000024687',
					goodsName: '农夫山泉',
					price: 10.00,
					storeList: [{
						storeCode: '00228',
						storeName: '华联超市田林店',
						basePrice: 15.00,
						status: 1
					}]
				}, {
					code: '132456-21',
					goodsId: '1000024687',
					goodsName: '农夫山泉',
					price: 10.00,
					storeList: [{
						storeCode: '00228',
						storeName: '华联超市田林店',
						basePrice: 15.00,
						status: 0
					}]
				}],
				// 搜索商品条件
				goodsSearchData: {
					goodsName: '',
					goodsCode: ''
				},
				// 搜索结果
				goodsSearchResult: [{
					goodsCode: '132456798',
					goodsName: '可口可乐新年装',
					goodsSize: '300ml',
					goodsStatus: 1
				}]
			}
		},
		// 生命周期方法
		// 初始数据和方法就绪，dom元素未挂载
		created() {
			// 请求配置参数
			var config = {
				// `url` is the server URL that will be used for the request
				url: '/user',

				// `method` is the request method to be used when making the request
				method: 'get', // default

				// `baseURL` will be prepended to `url` unless `url` is absolute.
				// It can be convenient to set `baseURL` for an instance of axios to pass relative URLs
				// to methods of that instance.
				baseURL: 'https://some-domain.com/api/',

				// `transformRequest` allows changes to the request data before it is sent to the server
				// This is only applicable for request methods 'PUT', 'POST', and 'PATCH'
				// The last function in the array must return a string or an instance of Buffer, ArrayBuffer,
				// FormData or Stream
				// You may modify the headers object.
				transformRequest: [function(data, headers) {
					// Do whatever you want to transform the data

					return data;
				}],

				// `transformResponse` allows changes to the response data to be made before
				// it is passed to then/catch
				transformResponse: [function(data) {
					// Do whatever you want to transform the data

					return data;
				}],

				// `headers` are custom headers to be sent
				headers: {
					'X-Requested-With': 'XMLHttpRequest'
				},

				// `params` are the URL parameters to be sent with the request
				// Must be a plain object or a URLSearchParams object
				params: {
					ID: 12345
				},

				// `data` is the data to be sent as the request body
				// Only applicable for request methods 'PUT', 'POST', and 'PATCH'
				// When no `transformRequest` is set, must be of one of the following types:
				// - string, plain object, ArrayBuffer, ArrayBufferView, URLSearchParams
				// - Browser only: FormData, File, Blob
				// - Node only: Stream, Buffer
				data: {
					firstName: 'Fred'
				},

				// `timeout` specifies the number of milliseconds before the request times out.
				// If the request takes longer than `timeout`, the request will be aborted.
				timeout: 1000,

				// `withCredentials` indicates whether or not cross-site Access-Control requests
				// should be made using credentials
				withCredentials: false, // default

				// `adapter` allows custom handling of requests which makes testing easier.
				// Return a promise and supply a valid response (see lib/adapters/README.md).
				adapter: function(config) {
					/* ... */
				},

				// `auth` indicates that HTTP Basic auth should be used, and supplies credentials.
				// This will set an `Authorization` header, overwriting any existing
				// `Authorization` custom headers you have set using `headers`.
				auth: {
					username: 'janedoe',
					password: 's00pers3cret'
				},

				// `responseType` indicates the type of data that the server will respond with
				// options are 'arraybuffer', 'blob', 'document', 'json', 'text', 'stream'
				responseType: 'json', // default

				// `xsrfCookieName` is the name of the cookie to use as a value for xsrf token
				xsrfCookieName: 'XSRF-TOKEN', // default

				// `xsrfHeaderName` is the name of the http header that carries the xsrf token value
				xsrfHeaderName: 'X-XSRF-TOKEN', // default

				// `onUploadProgress` allows handling of progress events for uploads
				onUploadProgress: function(progressEvent) {
					// Do whatever you want with the native progress event
				},

				// `onDownloadProgress` allows handling of progress events for downloads
				onDownloadProgress: function(progressEvent) {
					// Do whatever you want with the native progress event
				},

				// `maxContentLength` defines the max size of the http response content allowed
				maxContentLength: 2000,

				// `validateStatus` defines whether to resolve or reject the promise for a given
				// HTTP response status code. If `validateStatus` returns `true` (or is set to `null`
				// or `undefined`), the promise will be resolved; otherwise, the promise will be
				// rejected.
				validateStatus: function(status) {
					return status >= 200 && status < 300; // default
				},

				// `maxRedirects` defines the maximum number of redirects to follow in node.js.
				// If set to 0, no redirects will be followed.
				maxRedirects: 5, // default


				// 'proxy' defines the hostname and port of the proxy server
				// Use `false` to disable proxies, ignoring environment variables.
				// `auth` indicates that HTTP Basic auth should be used to connect to the proxy, and
				// supplies credentials.
				// This will set an `Proxy-Authorization` header, overwriting any existing
				// `Proxy-Authorization` custom headers you have set using `headers`.
				proxy: {
					host: '127.0.0.1',
					port: 9000,
					auth: {
						username: '',
						password: ''
					}
				},
			}
			axios(config).then(res => {
				console.log('-请求成功', res)
			}, fail => {
				console.log('-异步请求出错了', fail)
			})
			console.log("创建活动")

		},
		// 自定义方法
		methods: {
			init() {

			},
			deleteRow(index) {
				console.log(index)
			},
			addStore() {
				this.addStoreDialog = !this.addStoreDialog
			},
			next() {
				this.activeTab = 'b'
			},
			isWarnTxt({
				columnIndex
			}) {
				return columnIndex === 5 ? 'warn-color' : ''
			},
			addGodds() {
				this.goodsDialog = true;
			}
		},

	}
	global.helpAgeApp = new Vue(helpAge)
})(this)