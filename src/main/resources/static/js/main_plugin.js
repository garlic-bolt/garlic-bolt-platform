function animate_logo(){
	$('#logo')
	.removeClass()
	.addClass('animated rubberBand')
	.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
      $(this).removeClass();
    });
}

function initialization () {

	$('.custom_scrollbar').tinyscrollbar();
	$('.custom_scrollbar2').tinyscrollbar();

	//ui popup
	$('.popup').popup();

	//rating js
	$('.ui.rating').rating();

	$('.ui.accordion').accordion();

	// autogrowing textarea
	autogrowing_textarea();
	
	//tabs
	$('.demo.menu .item').tab({history: false});

	//checkbox
	$('.ui.checkbox').checkbox();

	//form dropdowns
	$('.ui.selection.dropdown').dropdown();

	//radio buttons
	$('.ui.radio.checkbox').checkbox();

	$('.chart').easyPieChart({size: 80,barColor:'#00B5AD'});

	$('.ui.form_validation').form(form_validation_options);

	//enable dropdown
	$('.dropdown').dropdown({
		debug: false,
		on: 'hover',
		transition: 'scale'
	});

	dropdowns();

	$('#gallery').least({'scrollToGallery':false});

	sparklines();

	dimmers();

	$('#footable').footable();

	morris_chart();

	animate_logo();
}

var form_validation_options  = {
	firstName: {
		identifier  : 'first-name',
		rules: [{
			type   : 'empty',
			prompt : 'Please enter your first name'
		}]
	},
	lastName: {
		identifier  : 'last-name',
		rules: [{
			type   : 'empty',
			prompt : 'Please enter your last name'
		}]
	},
	username: {
		identifier : 'username',
		rules: [{
			type   : 'empty',
			prompt : 'Please enter a username'
		}]
	},
	password: {
		identifier : 'password',
		rules: [{
			type   : 'empty',
			prompt : 'Please enter a password'
		},
		{
			type   : 'length[6]',
			prompt : 'Your password must be at least 6 characters'
		}]
	},
	terms: {
		identifier : 'terms',
		rules: [{
			type   : 'checked',
			prompt : 'You must agree to the terms and conditions'
		}]
	}
};

function morris_chart(){

	if ($('#graph').length){
		$('#graph').html('');

		Morris.Area({
			element: 'graph',
			behaveLikeLine: true,
			gridTextColor: '#fff',
			data: [
			{x: '2009', y: 1, z: 1},
			{x: '2010', y: 2, z: 1},
			{x: '2011', y: 6, z: 4},
			{x: '2012', y: 5, z: 4},
			{x: '2013', y: 6, z: 5},
			{x: '2014', y: 9, z: 8}
			],
			xkey: 'x',
			ykeys: ['z','y'],
			labels: ['Sales', 'Visits'],			
			pointFillColors: ['#000', '#000'],
			pointStrokeColors:'#42caf6',
			lineWidth: '6'
		});
	}

	if ($('#donut-example').length){
		$('#donut-example').html('');

		Morris.Donut({
			element: 'donut-example',
			gridTextColor: '#fff',
			data: [
			{label: "Sales from Facebook", value: 21},
			{label: "Sales from store", value: 10},
			{label: "Sales from Email", value: 4},
			{label: "Sales from ThemeForest", value: 65}
			],
			colors:[
			'#6ABBED',
			'#DABB1E',
			'#DE6A6E',
			'#78A04F',
			]
		});
	}

	if ($('#points').length){
		$('#points').html('');

		var day_data = [
		{"period": "2012-10-01", "licensed": 3407, "sorned": 660},
		{"period": "2012-09-30", "licensed": 3351, "sorned": 629},
		{"period": "2012-09-29", "licensed": 3269, "sorned": 618},
		{"period": "2012-09-20", "licensed": 3246, "sorned": 661},
		{"period": "2012-09-19", "licensed": 3257, "sorned": 667},
		{"period": "2012-09-18", "licensed": 3248, "sorned": 627},
		{"period": "2012-09-17", "licensed": 3171, "sorned": 660},
		{"period": "2012-09-16", "licensed": 3171, "sorned": 676},
		{"period": "2012-09-15", "licensed": 3201, "sorned": 656},
		{"period": "2012-09-10", "licensed": 3215, "sorned": 622}
		];
		Morris.Line({
			element: 'points',
			data: day_data,
			xkey: 'period',
			ykeys: ['licensed', 'sorned'],
			labels: ['Licensed', 'SORN']
		});
	}

	if ($('#bars').length){
		$('#bars').html('');

		Morris.Bar({
			element: 'bars',
			data: [
			{x: '2011 Q1', y: 4},
			{x: '2011 Q2', y: 1},
			{x: '2011 Q3', y: 2},
			{x: '2011 Q4', y: 3},
			{x: '2012 Q1', y: 4},
			{x: '2012 Q2', y: 5},
			{x: '2012 Q3', y: 6},
			{x: '2012 Q4', y: 7},
			{x: '2013 Q1', y: 8}
			],
			xkey: 'x',
			ykeys: ['y'],
			labels: ['Y'],
			barColors: function (row, series, type) {
				return '#DB0731';
			}
		});
	}
}

//DIMMERS
function dimmers(){

	$(document)
	.on('mouseover', '.image_dimmer_slide_down', function(event) {
		event.preventDefault();
		/* Act on the event */
		$(this)		
			.dimmer('setting', 'transition', 'slide down')
			.dimmer('show');

	})
	.on('mouseleave', '.image_dimmer_slide_down', function(event) {
		event.preventDefault();

		$(this)		
			.dimmer('setting', 'transition', 'slide down')
			.dimmer('hide');
	});

	$(document)
	.on('mouseover', '.image_dimmer_fade', function(event) {
		event.preventDefault();
		/* Act on the event */
		$(this)		
			.dimmer('setting', 'transition', 'fade')
			.dimmer('show');

	})
	.on('mouseleave', '.image_dimmer_fade', function(event) {
		event.preventDefault();

		$(this)		
			.dimmer('setting', 'transition', 'fade')
			.dimmer('hide');
	});

	$(document)
	.on('mouseover', '.image_dimmer_vertical_flip', function(event) {
		event.preventDefault();
		/* Act on the event */
		$(this)		
			.dimmer('setting', 'transition', 'vertical flip')
			.dimmer('show');
	})
	.on('mouseleave', '.image_dimmer_vertical_flip', function(event) {
		event.preventDefault();

		$(this)		
			.dimmer('setting', 'transition', 'vertical flip')
			.dimmer('hide');
	});

	$(document)
	.on('mouseover', '.image_dimmer_slide_up', function(event) {
		event.preventDefault();
		/* Act on the event */
		$(this)		
			.dimmer('setting', 'transition', 'slide up')
			.dimmer('show');
	})
	.on('mouseleave', '.image_dimmer_slide_up', function(event) {
		event.preventDefault();

		$(this)		
			.dimmer('setting', 'transition', 'slide up')
			.dimmer('hide');
	});

}
// DIMMERS

//DROPDOWNS
function dropdowns(){
	$('.animations .fade_up .dropdown').dropdown({
		debug: false,
		on: 'hover',
		transition: 'fade up'
	});

	$('.animations .pulse .dropdown').dropdown({
		debug: false,
		on: 'hover',
		transition: 'pulse'
	});

	$('.animations .tada .dropdown').dropdown({
		debug: false,
		on: 'hover',
		transition: 'tada'
	});

	$('.animations .horizontal_flip .dropdown').dropdown({
		debug: false,
		on: 'hover',
		transition: 'horizontal flip'
	});

	$('.animations .slide_down .dropdown').dropdown({
		debug: false,
		on: 'hover',
		transition: 'slide down'
	});
}
// DROPDOWNS


// MODALS

// basic modal
$(document).on('click', '.basic_modal', function(e) {
	e.preventDefault();
	$('.ui .test_basic').modal('toggle');
});

//Conditional Modal
$(document).on('click', '.conditional_modal', function(e) {
	e.preventDefault();
	$('.ui .test_conditional').modal('toggle');
});

// Small Modal
$(document).on('click', '.small_modal', function(e) {
	e.preventDefault();
	$('.ui .test_small').modal('toggle');
});

// Large Modal
$(document).on('click', '.large_modal', function(e) {
	e.preventDefault();
	$('.ui .test_large').modal('toggle');
});

// Horizontal Flip Modal
$(document).on('click', '.horizontal_flip_modal', function(e) {
	e.preventDefault();
	$('.ui .test_basic').modal('setting','transition' ,'horizontal flip').modal('toggle');
});


// Vertical Flip Modal
$(document).on('click', '.vertical_flip_modal', function(e) {
	e.preventDefault();
	$('.ui .test_basic').modal('setting','transition' ,'vertical flip').modal('toggle');
});

// Fade Up Modal
$(document).on('click', '.fade_up_modal', function(e) {
	e.preventDefault();
	$('.ui .test_basic').modal('setting','transition' ,'fade up').modal('toggle');
});

// Fade Modal
$(document).on('click', '.fade_modal', function(e) {
	e.preventDefault();
	$('.ui .test_basic').modal('setting','transition' ,'fade').modal('toggle');
});

// Scale Modal
$(document).on('click', '.scale_modal', function(e) {
	e.preventDefault();
	$('.ui .test_basic').modal('setting','transition' ,'scale').modal('toggle');
});

// Slide Up Modal
$(document).on('click', '.slide_up_modal', function(e) {
	e.preventDefault();
	$('.ui .test_basic').modal('setting','transition' ,'slide up').modal('toggle');
});

// Tada Modal
$(document).on('click', '.tada_modal', function(e) {
	e.preventDefault();
	$('.ui .test_small').modal('setting','transition' ,'tada').modal('toggle');
});

// MODALS

function sparklines(){

	var sidebarstats = [10,8,5,7,4,4,11,10,8,5,7,4,4,10,10,8,5,7,11],
	sidebarstats2 = [10,8,5,7,4,4,11,8,8,5,7,4,4,10,10,8,5,7,9],
	sidebarstats3 = [10,8,5,7,4,4,11,8,8,5,7,4,4,8,9,10,11,12,13],
	bars = [10,8,5,7,4,4,1],
	bars2 = [10,8,5,7,4,4,10],
	bars3 = [10,8,5,7,5,6,1],
	bars4 = [10,8,5,7,8,4,9];

	$('.sidebarstats').sparkline(sidebarstats, {
		type: 'bar',
		barWidth: 9,
		zeroAxis: false,
		barColor: '#78A04F'
	});

	$('.sidebarstats2').sparkline(sidebarstats2, {
		type: 'bar',
		barWidth: 9,
		zeroAxis: false,
		barColor: '#6ABBED'
	});

	$('.sidebarstats3').sparkline(sidebarstats3, {
		type: 'bar',
		barWidth: 9,
		zeroAxis: false,
		barColor: '#DE6A6E'
	});

	$('.dynamicsparkline').sparkline(bars, {
		type: 'bar',
		barWidth: 6,
		zeroAxis: false,
		barColor: '#78A04F'
	});

	$('.dynamicsparkline3').sparkline(bars3, {
		type: 'bar',
		barWidth: 6,
		zeroAxis: false,
		barColor: '#DE6A6E'
	});

	$('.dynamicsparkline2').sparkline(bars2, {
		type: 'bar',
		barWidth: 6,
		zeroAxis: false,
		barColor: '#ffc600'
	});


	$('.dynamicsparkline4').sparkline(bars4, {
		type: 'bar',
		barWidth: 6,
		zeroAxis: false,
		barColor: '#42caf6'
	});
}

// autogrowing textarea
function autogrowing_textarea() {
	$(".autogrow").mousemove(function(e) {
		var myPos = $(this).offset();
		myPos.bottom = $(this).offset().top + $(this).outerHeight();
		myPos.right = $(this).offset().left + $(this).outerWidth();

		if (myPos.bottom > e.pageY && e.pageY > myPos.bottom - 16 && myPos.right > e.pageX && e.pageX > myPos.right - 16) {
			$(this).css({
				cursor: "nw-resize"
			});
		} else {
			$(this).css({
				cursor: ""
			});
		}
	}).keyup(function(e) {
		//  the following will help the text expand as typing takes place
		while ($(this).outerHeight() < this.scrollHeight + parseFloat($(this).css("borderTopWidth")) + parseFloat($(this).css("borderBottomWidth"))) {
			$(this).height($(this).height() + 1);
		};
	});
}