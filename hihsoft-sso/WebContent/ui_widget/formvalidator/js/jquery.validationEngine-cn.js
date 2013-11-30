(function($) {
	$.fn.validationEngineLanguage = function() {
	};
	$.validationEngineLanguage = {
		newLang : function() {
			$.validationEngineLanguage.allRules = {
				"required" : { // Add your regex rules here, you can take
								// telephone as an example
					"regex" : "none",
					"alertText" : "* 必填项",
					"alertTextCheckboxMultiple" : "* 请选择一个选项",
					"alertTextCheckboxe" : "* 必选项"
				},
				"minSize" : {
					"regex" : "none",
					"alertText" : "* 最少 ",
					"alertText2" : " 个字符"
				},
				"maxSize" : {
					"regex" : "none",
					"alertText" : "* 最多 ",
					"alertText2" : " 个字符"
				},
				"min" : {
					"regex" : "none",
					"alertText" : "* 最小数是 "
				},
				"max" : {
					"regex" : "none",
					"alertText" : "* 最大数是 "
				},
				"past" : {
					"regex" : "none",
					"alertText" : "* 需晚于 "
				},
				"future" : {
					"regex" : "none",
					"alertText" : "* 需晚于 "
				},
				"maxCheckbox" : {
					"regex" : "none",
					"alertText" : "* 最多 ",
					"alertText2" : " 个选项"
				},
				"minCheckbox" : {
					"regex" : "none",
					"alertText" : "* 请选择 ",
					"alertText2" : " 个选项"
				},
				"equals" : {
					"regex" : "none",
					"alertText" : "* 字段不匹配"
				},
				"phone" : {
					// credit: jquery.h5validate.js / orefalo
					"regex" : /^([\+][0-9]{1,3}[ \.\-])?([\(]{1}[0-9]{2,6}[\)])?([0-9 \.\-\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/,
					"alertText" : "* 电话号码格式不对"
				},
				"fax" : {
					// credit: jquery.h5validate.js / orefalo
					"regex" : /^([\+][0-9]{1,3}[ \.\-])?([\(]{1}[0-9]{2,6}[\)])?([0-9 \.\-\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/,
					"alertText" : "* 传真号码格式不对"
				},
				"email" : {
					// Shamelessly lifted from Scott Gonzalez via the
					// Bassistance Validation plugin
					// http://projects.scottsplayground.com/email_address_validation/
					"regex" : /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
					"alertText" : "* 邮箱格式不对"
				},
				"integer" : {
					"regex" : /^[\-\+]?\d+$/,
					"alertText" : "* 无效的整数"
				},
				"number" : {
					// Number, including positive, negative, and floating
					// decimal. credit: orefalo
					"regex" : /^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/,
					"alertText" : "* 无效的小数"
				},
				"date" : {
					"regex" : /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/,
					"alertText" : "* 无效的日期，例如：2010-01-01"
				},
				"ipv4" : {
					"regex" : /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/,
					"alertText" : "* 无效的IP地址"
				},
				"url" : {
					"regex" : /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i,
					"alertText" : "* 无效的URL路径"
				},
				"onlyNumberSp" : {
					"regex" : /^[0-9\ ]+$/,
					"alertText" : "* 只能是数字"
				},
				"onlyLetterSp" : {
					"regex" : /^[a-zA-Z\ \']+$/,
					"alertText" : "* 只能是字符"
				},
				"onlyLetterNumber" : {
					"regex" : /^[0-9a-zA-Z]+$/,
					"alertText" : "* 只能是数字和字符"
				},
				// --- CUSTOM RULES -- Those are specific to the demos, they can
				// be removed or changed to your likings
				"ajaxUserCall" : {
					"url" : "ajaxValidateFieldUser",
					// you may want to pass extra data on the ajax call
					"extraData" : "name=eric",
					"alertText" : "* 这位用户已经被注册",
					"alertTextLoad" : "* 验证中, 请稍后..."
				},
				"ajaxUserCallPhp" : {
					"url" : "phpajax/ajaxValidateFieldUser.php",
					// you may want to pass extra data on the ajax call
					"extraData" : "name=eric",
					// if you provide an "alertTextOk", it will show as a green
					// prompt when the field validates
					"alertTextOk" : "* 此用户名可用",
					"alertText" : "* 这位用户已经被注册",
					"alertTextLoad" : "* 验证中, 请稍后..."
				},
				"ajaxNameCall" : {
					// remote json service location
					"url" : "taclUserinfoController.do?method=testUserName",
					// error
					"alertText" : "* 这位用户已经被注册",
					// if you provide an "alertTextOk", it will show as a green
					// prompt when the field validates
					"alertTextOk" : "* 此用户名可用",
					// speaks by itself
					"alertTextLoad" : "* 验证中, 请稍后..."
				},
				"ajaxNameCallPhp" : {
					// remote json service location
					"url" : "phpajax/ajaxValidateFieldName.php",
					// error
					"alertText" : "* 这位用户已经被注册",
					// speaks by itself
					"alertTextLoad" : "* 验证中, 请稍后..."
				},
				"ajaxOrgnoCall" : {
					// remote json service location
					"url" : "tsysOrgController.do?method=testOrgno",
					// error
					"alertText" : "* 这个单位编号已被注册",
					// speaks by itself
					"alertTextLoad" : "* 验证中, 请稍后...",
					
					"alertTextOk" : "* 此单位编号可用"
				},
				"validate2fields" : {
					"alertText" : "* Please input HELLO"
				},
				//手机号码add by xjf20110624
				"mobile" : {
					"regex" : /^0*(13|15|18)\d{9}$/,
					"alertText" : "* 请输入正确的手机号"
				},
				//身份证add by xjf20110624
				"idNumber" : {
					"regex" : /^\d{15}|\d{18}$/,
					"alertText" : "* 请输入正确的身份证号码"
				}
			};

		}
	};
	$.validationEngineLanguage.newLang();
})(jQuery);