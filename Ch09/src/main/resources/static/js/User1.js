/**
 * 
 */
		$(document).ready(function(){
				
				//user1 목록
				$('.user1.list1').click(function(){
					
					console.log('click');
					
					$.ajax({
						url: '/Ch09/user1',
						method: 'GET',
						dataType: 'json',
						success: function(data){
							console.log(data);
						}
					});
					
				});
				$('.user1.list2').click(function(){
					
					let uid = 'a104';
					
					$.ajax({
						url: '/Ch09/user1/'+uid,
						method: 'GET',
						dataType: 'json',
						success: function(data){
							console.log(data);
						}
					});
				});
				$('.user1.register').click(function(){
					
					let jsonData = {
						"uid" : "s101",
						"name" : "홍홍삼",
						"hp" : "010-3234-5677",
						"age" : 19
					};
					
					$.ajax({
						url: '/Ch09/user1',
						method: 'POST',
						data: jsonData,
						dataType: 'json',
						success: function(data){
							console.log(data);
						}
					});
					
				});
				
				$('.user1.modify').click(function(){
					
					let jsonData = {
							"uid" : "s101",
							"name" : "홍홍삼",
							"hp" : "010-3234-5677",
							"age" : 19
						};
						
						$.ajax({
							url: '/Ch09/user1',
							method: 'PUT',
							data : jsonData,
							dataType: 'json',
							success: function(data){
								console.log(data);
							}
						});
					
				});
				$('.user1 delete').click(function(){
					
					let uid = 'a104';
					
					$.ajax({
						url: '/Ch09/user1/'+uid,
						method: 'DELETE',
						dataType: 'json',
						success: function(data){
							console.log(data);
						}
					});
					
					
				});
				
			});
