$('#side_scrollbar').css('height', $(window.top).height());
$('.custom_scrollbar2').tinyscrollbar();

var window_height = $(window).width();

$(window).resize(function(event) {
	$('#side_scrollbar').css('height', $(window.top).height());
	$('.custom_scrollbar2').tinyscrollbar();
	morris_chart();

	window_height = $(window).width();
	if (window_height > 868)
	{
		$('#side').sidebar('show', function(){
			setTimeout(function(){
				morris_chart();
			}, 800);
		});
	}
	else {
		$('#side').sidebar('hide');
	}
});


$(document).ready(function() {

	if (window_height > 868)
	{
		$('#side').sidebar('show', function(){
			setTimeout(function(){
				morris_chart();
			}, 800);
		});
	}

	// INITIALIZATION
	initialization();

	var url = window.location.pathname
	filename = url.substring(url.lastIndexOf('/') + 1),
		History = window.History, // Note: We are using a capital H instead of a lower h
		State = History.getState();

	$('#side_scrollbar').css('height', $(window).height());

	$('.custom_scrollbar2').tinyscrollbar();

	$('.message .close').on('click', function(e) {
		e.preventDefault();
		$(this).closest('.message').fadeOut();
	});

	$(document).on('click', '#lunch_settings', function(event) {
		event.preventDefault();
		$('#settings_bar').sidebar({
			overlay: true
		}).sidebar('toggle');
	});

	$(document).on('click', '#lunch_sidebar', function(event) {
		event.preventDefault();
		$('#side').sidebar('toggle');

		if ($('.is_home').length){
			setTimeout(function(){
				morris_chart();
			}, 200);
		}
	});

	$(document).on('click', '.menus_page .menu .item', function(event) {
		event.preventDefault();

		$(this).parent().find('.item').removeClass('active');

		$(this).addClass('active');
	});

	$(document).on('mouseover', '#lunch_sidebar', function(event) {
		event.preventDefault();
		$(this).find('.reorder').transition('pulse');
	});

	if ($('.is_home').length){
		$('.new_alerts .column').find('.segment').transition('tada');
	}

	// SIDEBAR
	$('.sidebar_dropdown').on('click',function(e) {

		$('.sidebar .menu_dropdown').each(function() {
			$(this).slideUp();
			$(this).parent().find('.triangle').addClass('left');
		});

		var menu = $(this).next('.menu_dropdown'),
			icon = $(this).find('.triangle');

		if (menu.is(":visible") == false) {
			icon.removeClass('left').addClass('down');
			menu.css({
				visibility: "visible",
				display: "none"
			}).slideDown('slow', function(){
				$('.custom_scrollbar2').tinyscrollbar();
			});
		}
		else{
			icon.removeClass('down').addClass('left');
			menu.css({
				visibility: "visible",
				display: "block"
			}).slideUp('slow', function(){
				$('.custom_scrollbar2').tinyscrollbar();
			});
		}
	});
	// SIDEBAR

	if (filename && filename != 'index.php'){
		var menu = $('#side'),
			link = $('a[href="'+ filename +'"]');

		$('#home').removeClass('active');

		if (link.parents('.sidebar_dropdown').length)
		{
			link.closest('.sidebar_dropdown').trigger('click');
			link.parent().addClass('active');
			return;
		}

		link.addClass('active');
	}

	$(document).on('click', 'a.ajax-link, #side .secondary .item', function(e) {
		e.preventDefault();

		if ($(this).parents('.settings').length){
			return;
		}

		var self = $(this),
			link_href = self.attr('href');

		self.append('<div class="ui active inline mini loader"></div>');

		if ( ! link_href ){
			self = $(this).find('a.ajax-link');
			link_href = self.attr('href');
		}

		$("#side").find('.item, .menu_dropdown').each(function() {
			$(this).removeClass('active');
		});

		if (self.parents('.menu_dropdown').length){
			self.parents('.menu_dropdown').addClass('active');
		}else{
			$('.sidebar .menu_dropdown').each(function() {
				$(this).slideUp();
				$(this).parent().find('.triangle').addClass('left');
			});
		}

		History.pushState(null, null, link_href);
		$('ul.main-menu li.active').removeClass('active');


		self.addClass('active');

		$("#home").removeClass('active');

		if ($(self)[0].href == String(window.location)) {
			$(".ajax-link").removeClass('active');
			self.addClass('active');
		}

		initialization();

		$('#side .loader').remove();

		if (window_height < 868)
		{
			$('#side').sidebar('hide');
		}

	});

	//bind to State Change
	History.Adapter.bind(window, 'statechange', function() { // Note: We are using statechange instead of popstate
		var State = History.getState(); // Note: We are using History.getState() instead of event.state
		$.ajax({
			url: State.url,
			success: function(response) {

				var content = $(response).find('#content').html();

				$('#content').html(content).hide().fadeIn();
				$('title').text('Sample Admin');
				initialization();
			}
		});
	});

	$(document).on('click', '.btn-setting', function(e) {
		e.preventDefault();

		$('.modal_settings')
			.modal('setting', 'transition', 'vertical flip')
			.modal('setting', 'debug', 'false')
			.modal('toggle');
	});

	$(document).on('click', '.btn-close', function(e) {
		e.preventDefault();

		var self = $(this);

		setTimeout(function() {
			self.parents('.box').fadeOut();
		}, 500);

	});

	$(document).on('click', '.btn-minimize', function(e) {
		e.preventDefault();
		var elm = $(this).parents('.box').find('.box-content');

		if (elm.is(':visible')){
			elm.fadeOut({easing : 'easeInCirc'});
			$(this).find('i').removeClass('up').addClass('down');
		}
		else{
			elm.fadeIn({easing : 'easeInCirc'});
			$(this).find('i').removeClass('down').addClass('up');
		}

	});

	$(document).on('click', '.reload', function(e) {
		e.preventDefault();

		var loader = $(this).parents('.box').find('.dimmer');

		loader.addClass('active');

		setTimeout(function() {
			loader.removeClass('active');
		}, 2000);
	});

	$(document).on('click', '.submit', function(event) {
		event.preventDefault();
		/* Act on the event */
		var self = $(this);
		self.addClass('loading');

		setTimeout(function() {
			self.removeClass('loading');
		}, 1000);
	});

	// Add comemnts list
	$(document).on('click', '#add_comment', function(event) {
		event.preventDefault();
		/* Act on the event */
		var self = $(this),
			comment_obj = self.parent().find('input'),
			comment = comment_obj.val(),
			scroll = $('.comments .custom_scrollbar').tinyscrollbar(),
			comments_panel = self.parents('.comments'),
			html_element = '<div class="comment"><a class="avatar"><img src="assets/img/avatar.jpg"></a><div class="content"><a class="author">You</a><div class="metadata"><div class="date">today</div></div><div class="text">'+comment+'</div></div></div>';

		if ( comment == ''){
			comment_obj.parents('.field').addClass('error');
			return false;
		}
		else{
			comment_obj.parents('.field').removeClass('error');
		}

		comments_panel.find('.overview').append(html_element);

		var first_overview = comments_panel.find('.overview:first'),
			view_port = comments_panel.find('.viewport:first'),
			thumb = comments_panel.find('.thumb:first') ,
			track = comments_panel.find('.track:first') ;

		first_overview.css({top: ((first_overview.height() - view_port.height()) * (-1)) });
		thumb.css({top: track.height() - thumb.height()});
		comment_obj.val('');
	});

	$(document).on('click', '.tasks_list .checkbox', function(event) {
		event.preventDefault();

		var elm = $(this).parents('.item').find('.ribbon');

		if (elm.hasClass('awesome_red')){
			elm.addClass('teal').removeClass('awesome_red').text('Done');
		}else{
			elm.addClass('awesome_red').removeClass('teal').text('Not Done');
		}

	});

	$(document).on('focusin', '.textarea-reply', function(event) {
		$(this).css('height', '100px');
		$('.show-buttons').fadeIn();
	}).on('focusout', '.textarea-reply',function(event) {
		$('.show-buttons').fadeOut();
		$(this).css('height', '38px');
	});

	function animate(type) {
		$('#animated_subject').removeClass().addClass(type + ' animated')
			.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
				$(this).removeClass();
			});
	};

	$(document).on('change', '.test_animations', function(){
		animate($(this).val());
	});

});


$(document).on('click',  function(event) {

	var _target = $(event.target);

	if (_target.closest('#settings_bar').length)
	{
		return false;
	}

	// console.log(_target.parents());

// 	if ( 
// 		! _target.parents('div#side').length 
// 		&& ! _target.hasClass('side') 
// 		&& ! _target.hasClass('reorder') 
// 		&& ! _target.hasClass('settings_bar') 
// 		) {
// 		$('.ui.sidebar').sidebar('hide');
// }

	if ( _target.hasClass('lunch_sidebar')) {
		if ($('.is_home').length || $('.is_chars').length){
			setTimeout(function(){
				morris_chart();
			}, 200);
		}
	};

});

// SETTINGS js

$(document).on('click', '.change_wide', function(event) {
	event.preventDefault();

	var _value = $(this).data('type'),
		_div = $("#general_container");
	$('.loader').remove();
	console.log('sadas');

	if (_value !== ''){

		if (_value == 'fluid'){
			_div.removeClass('page').addClass('one column');
			$(this).addClass('active');
			$('*[data-type="boxed"]').removeClass('active');
		}

		if(_value == 'boxed'){
			_div.removeClass('one column').addClass('page');
			$(this).addClass('active');
			$('*[data-type="fluid"]').removeClass('active');
		}

		morris_chart();
	}

});

$(document).on('change', '#navbar_fixed', function(event) {
	event.preventDefault();
	/* Act on the event */
	var _elm = $(".navbar-inner ");

	if($(this).is(':checked'))
		_elm.css('position', 'fixed');
	else
		_elm.css('position', 'absolute');

	$('#settings_bar').sidebar({
		overlay: true
	}).sidebar('toggle');

});

