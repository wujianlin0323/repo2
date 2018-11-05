(function(global) {
	var helpAge = {}
	helpAge.createUser = {
		el: "#helpAgeContent",
		data() {
			return {
				activeTab: 'first',
				// 搜索结果
				activeGoodsData: [{
					code: '132456-21',
					goodsId: '1000024687',
					goodsName: '农夫山泉',
					price: 10.00,
					storeList: [{
						storeCode: '00228',
						storeName: '华联超市田林店',
						basePrice: 15.00
					}]
				}, {
					code: '132456-21',
					goodsId: '1000024687',
					goodsName: '农夫山泉',
					price: 10.00,
					storeList: [{
						storeCode: '00228',
						storeName: '华联超市田林店',
						basePrice: 15.00
					}]
				}, {
					code: '132456-21',
					goodsId: '1000024687',
					goodsName: '农夫山泉',
					price: 10.00,
					storeList: [{
						storeCode: '00228',
						storeName: '华联超市田林店',
						basePrice: 15.00
					}]
				}]

			}
		},
		methods: {

		}
	}
	global.helpAgeApp = new Vue(helpAge.createUser)
})(this)