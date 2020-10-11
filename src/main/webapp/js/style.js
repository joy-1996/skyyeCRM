function checkAll(i, str) {
	var arr = [
	// 检查供应商名称和种类名称 不能为空
	/^[\u4e00-\u9fa5]{2,10}$/,
	// 检查是否为数字{1,8}不能为空
	/^[0-9|.]{1,8}$/,
	/^[|a-z||A-Z||0-9|]{2,10}$/
	];
	return arr[i].test(str);
}
//产品名称
function checkPname(obj,i) {
	
	var v = obj.value;
	var result = checkAll(0, v);
	var fn = document.getElementById("pname");
	if (result) {
		fn.innerHTML = "<font color='green'>名称符合规范</font>";
		
		return result;
	} else {
		fn.innerHTML = "<font color='red'>名称只能为中文并且不能为空</font>";
		 
		return false;
	}
	return result; 
	
}
function checkName(obj,i) {
	var v = obj.value;
	var result = checkAll(2, v);
	var fn = document.getElementById("name");
	if (result) {
		fn.innerHTML = "<font color='green'>名称符合规范</font>";
		return result;
	} else {
		fn.innerHTML = "<font color='red'>名称应为大小写字母与数字长度2-10</font>";
		return false;
	}
	return result; 
}
function checkPassword(obj,i) {
	
	var v = obj.value;
	var result = checkAll(2, v);
	var fn = document.getElementById("password");
	if (result) {
		fn.innerHTML = "<font color='green'>密码符合规范</font>";
		
		return result;
	} else {
		fn.innerHTML = "<font color='red'>密码不符合规范</font>";
		 
		return false;
	}
	return result; 
	
}


//产品进价
function checkiprice(obj,i) {
	var v = obj.value;
	var result = checkAll(1,v);
	var fn = document.getElementById("iprice");
	if (result) {
		fn.innerHTML = "<font color='green'>商品信息符合规范</font>";
		return result;
	} else {
		fn.innerHTML = "<font color='red'>只能是数字并且不能为空</font>";
		
		return false;
	}
	return result;
}

//产品数量
function checkpquantity(obj,i) {
	var v = obj.value;
	var result = checkAll(1, v);
	

	var fn = document.getElementById("pquantity");
	if (result) {
		fn.innerHTML = "<font color='green'>商品信息符合规范</font>";
		reuslt = true;
		return result;
	} else {
		fn.innerHTML = "<font color='red'>只能是数字并且不能为空</font>";
		result = false;
		return result;
	}
	
}
//产品售价
function checksprice(obj,i) {
	var v = obj.value;
	var result = checkAll(1, v);

	
	
	var fn = document.getElementById("sprice");
	if (result) {
		fn.innerHTML = "<font color='green'>商品信息符合规范</font>";
		reuslt = true;
		return result;
	} else {
		fn.innerHTML = "<font color='red'>只能是数字并且不能为空</font>";
		result = false;
		return result;
	}
	
}
//进货时间
function checkitime(obj) {
	
	var fn = document.getElementById("itime");
	
	if (obj.value.length>0) {
		fn.innerHTML = "<font color='green'>商品信息符合规范</font>";
		return true;
	} else {
		fn.innerHTML = "<font color='red'>商品信息不能为空</font>";
		return false;
	}
	
}
function checkForm() {
	if(
	checkPname(document.getElementById("name"),0)
	&&checkiprice(document.getElementById("price"),1)
	&&checkpquantity(document.getElementById("quantity"),1)	
	&&checksprice(document.getElementById("pprice"),1)
	&&checkitime(document.getElementById("time")))
	{
		document.getElementById("submit").submit();
	}else{
		 event.preventDefault();
	}
}
function checkgister() {
	if(
		checkName(document.getElementById("pname"),2) && checkPassword(document.getElementById("rpassword"),2))
	{
		document.getElementById("submit").submit();
	}else{
		 event.preventDefault();
	}
}
