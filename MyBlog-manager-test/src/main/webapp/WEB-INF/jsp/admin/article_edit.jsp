
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/bootstrap4.0.min.css" >
    <script src="/js/jquery.slim.min.js" ></script>
    <script src="/js/popper.min.js" ></script>
    <script src="/js/bootstrap4.0.min.js"></script>
    <script type="text/javascript" src="/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="/js/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript"  src="/js/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<div style="position: relative;top: 10%">
    <c:if test="${!empty succ}">
        <div class="alert alert-success" role="alert">
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>
</div>
<div class="container">
    <form action="/admin/article/edit/do" method="post">
        <input type="hidden" value="${article.id}" name="id">
        <div class="form-group">
            <label for="title">文章标题</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="文章标题" value="${article.title}">
        </div>
        <div class="form-group">
            <label for="catalogId">栏目</label>
            <select class="form-control" id="catalogId" name="catalogId">
                <option value="0" <c:if test="${article.catalogId==0}">selected="selected"</c:if>>学习</option>
                <option value="1" <c:if test="${article.catalogId==1}">selected="selected"</c:if>>生活</option>
                <option value="2" <c:if test="${article.catalogId==2}">selected="selected"</c:if>>摘要</option>
                <option value="3" <c:if test="${article.catalogId==3}">selected="selected"</c:if>>人生随笔</option>
            </select>
        </div>
        <div class="form-group">
            <label for="keywords">关键字</label>
            <input type="text" class="form-control" id="keywords" name="keywords" placeholder="关键字" value="${article.keywords}">
        </div>
        <div class="form-group">
            <label for="desci">简介</label>
            <textarea class="form-control" id="desci" rows="3" name="desci" placeholder="简介">${article.desci}</textarea>
        </div>
        <div id="cont" style="display: none">
            ${article.content}
        </div>
        <div class="form-group">
            <label for="editor">内容</label>
            <script id="editor" type="text/plain"  name="content" style="width:1024px;height:500px;" >
            </script>
            </div>
            <input type="submit" />
                </form>	
                <script>
                $(function(){
                    var ue = UE.getEditor('editor');
                    ue.ready(function() {
                        ue.setContent($("#cont").html());
                    });
                });
            </script>
        </div>
       
</body>
 <script type="text/javascript">  
  
			//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
			function banBackSpace(e){     
			    var ev = e || window.event;//获取event对象     
			    var obj = ev.target || ev.srcElement;//获取事件源     
			      
			    var t = obj.type || obj.getAttribute('type');//获取事件源类型    
			      
			    //获取作为判断条件的事件类型  
			    var vReadOnly = obj.getAttribute('readonly');  
			    var vEnabled = obj.getAttribute('enabled');  
			    //处理null值情况  
			    vReadOnly = (vReadOnly == null) ? false : vReadOnly;  
			    vEnabled = (vEnabled == null) ? true : vEnabled;  
			      
			    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
			    //并且readonly属性为true或enabled属性为false的，则退格键失效  
			    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")   
			                && (vReadOnly==true || vEnabled!=true))?true:false;  
			     
			    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效  
			    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")  
			                ?true:false;          
			      
			    //判断  
			    if(flag2){  
			        return false;  
			    }  
			    if(flag1){     
			        return false;     
			    }     
			}  
			  
			//禁止后退键 作用于Firefox、Opera  
			document.onkeypress=banBackSpace;  
			//禁止后退键  作用于IE、Chrome  
			document.onkeydown=banBackSpace; 
			
			window.onload = banBackSpace;
		  
		</script>  
        
</html>