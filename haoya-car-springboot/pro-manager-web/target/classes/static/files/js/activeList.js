(function(global) {
	var helpAge = {}
	helpAge.createUser = {
		el: "#helpAgeContent",
		data() {
			return {
				visible: false,
				activeTab: 'first',
				queryCondition: {
					code: '',
					name: '',
					time: '',
					storeName: '',
					status: ''
				},
				// 搜索结果
				queryResult: [{
					number: '132456-21',
					name: '助老期刊名称',
					store: '中环百联',
					startTime: '2018-02-08',
					endTime: '2018-03-08',
					statusName: '草稿',
					status: '2'
				}, {
					number: '132456-21',
					name: '助老期刊名称',
					store: '中环百联',
					startTime: '2018-02-08',
					endTime: '2018-03-08',
					statusName: '待审核',
					status: '3'
				}, {
					number: '132456-21',
					name: '助老期刊名称',
					store: '中环百联',
					startTime: '2018-02-08',
					endTime: '2018-03-08',
					statusName: '已审核',
					status: '1'
				}, {
					number: '132456-21',
					name: '助老期刊名称',
					store: '中环百联',
					startTime: '2018-02-08',
					endTime: '2018-03-08',
					statusName: '已停用',
					status: '0'
				}]

			}
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						alert('submit!');
					} else {
						console.log('error submit!!');
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			}
		}
	}
	global.helpAgeApp = new Vue(helpAge.createUser)
})(this)