> ##roles:
> * admin:
>   > ROOT all;
>   > USERADMIN vipuserinfo,elderpeopleinfo;
>   > BEADHOUSEADMIN  beadhouseinfo
>   > LEISUREGROUPADMIN leisuregroup
>   > SYSTEMADMIN operating log, authorise ...
> * beadhouseadmin:
>   > ROOT all
>   > INFOADMIN beadhouse base info, comment reply
>   > ELDERADMIN add,delete,search,update logs
>   > SYSTEMADMIN operating logs
> * user:
>   > GUEST
>   > VIPUSER
        
        
##权限控制
        权限控制、权限申请、权限审批
        权限申请：申请之后的流程？
        权限审批：谁来批权限？
 
###权限审批流程
	判断用户是否具有该页面的控制权利，若有，则继续，否则页面跳转至该权限的申请页面。。。然后主管部门进行审批，主管部门审批完之后，由要申请的控制权的主管进行审批。审批完之后，用户获得相应的权限。
	
###数据表
	后台用户角色对应表
	部门表
	
###数据流和中间的存储
	权限申请以及权限审批的数据流向以及中间的存储过程？
	
###个人中心
	后台管理员会受到权限待审批或者权限已审批的通知。
	个人设置