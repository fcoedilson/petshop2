function initLoader() {
	resizeApp();
}

function openPentaho(){
    window.open();
	document.location.href='http://bi.cti.fortaleza.ce.gov.br:8084/pentaho/content/pentaho-cdf-dd/Render?solution=Frotas&amp;path=/Dashboard&amp;file=ug_periodo_consumo.wcdf';
}

function resizeApp() {

	//document.getElementById('content').style.height = (currentHeight() - 50) + 'px';
	document.getElementById('veiculoMonitora').style.height = (currentHeight() - 50) + 'px';

	try {
		var nameSize = document.getElementById('userLogin').firstChild.nodeValue.length;
		if( document.getElementById('innerPopup_body') ){
			document.getElementById('innerPopup_body').height = (currentHeight() - 230) + 'px';
		}
		if( document.getElementById('innerPopup') ){
			document.getElementById('innerPopup').height = (currentHeight()) + 'px';
		}
	} catch (e) {}
	window.onresize = resizeApp;
	openCloseList(false);
}

function currentWidth() {

    var width = 0;
    if (typeof(window.innerWidth) == 'number') {
    	width = window.innerWidth;
    } else if (document.documentElement && document.documentElement.clientWidth) {
    	width = document.documentElement.clientWidth;
    } else if (document.body && document.body.clientWidth) {
    	width = document.body.clientWidth;
    }
    return width;
}

function currentHeight() {

    var height = 0;
    if (typeof(window.innerHeight) == 'number') {
    	height = window.innerHeight;
    } else if (document.documentElement && document.documentElement.clientHeight) {
    	height = document.documentElement.clientHeight;
    } else if (document.body && document.body.clientHeight) {
    	height = document.body.clientHeight;
    }
    return height;
}



function openCloseList(reSize) {

	document.getElementById('veiculoMonitora').style.width = currentWidth() + 'px';
	
	if (reSize) {
		
//
//		if(document.getElementById('maparea')){
//			document.getElementById('maparea').style.height = (currentHeight() - 220) + 'px';
//		}

		document.getElementById('monitoramentoInfo').style.display = 'none';
		document.getElementById('imgOpen').style.display = '';
		document.getElementById('imgClose').style.display = 'none';

	} else {

		if (document.getElementById('monitoramentoInfo').style.display == '') {
			document.getElementById('veiculoMonitora').style.width = currentWidth() + 'px';
			document.getElementById('monitoramentoInfo').style.display = 'none';
			document.getElementById('veiculoMonitora').style.marginLeft =  '0px';
			document.getElementById('imgOpen').style.display = '';
			document.getElementById('imgClose').style.display = 'none';
		} else {
			document.getElementById('veiculoMonitora').style.width = (currentWidth()) + 'px';
			document.getElementById('monitoramentoInfo').style.display = '';
			document.getElementById('veiculoMonitora').style.marginLeft =  '450px';
			document.getElementById('imgOpen').style.display = 'none';
			document.getElementById('imgClose').style.display = '';
		}
	}

	try {
		map.checkResize();
		calculateBounds();
	} catch(err) {}

}
