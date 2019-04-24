/**
 * 
 */
new Vue ({
				el:'#app',
				data: {
					modalState: false,
				},
				methods:{
					changeState:function()
					{
						this.modalState=true;
					}
				},
				beforeMount(){
					this.changeState();
				}
			})