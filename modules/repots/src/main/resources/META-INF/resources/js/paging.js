(function($) {
    $(function() {
        $.widget("zpd.paging", {
            options: {
                limit: 5,
                rowDisplayStyle: 'block',
                activePage: 0,
                rows: []
            },
            _create: function() {
                var rows = $("tbody", this.element).children();
                this.options.rows = rows;
                if(rows.length==0){
                	/*$(this.element).replaceWith('<span id="emptyRecord" aria-live="assertive" role="alert" tabindex="-1" >No Records Found</span>');
					$("#emptyRecord").focus();*/
                }
                if(rows.length>0){
                	this.options.rowDisplayStyle = rows.css('display');
                    //var nav = this._getNavBar();
                    //this.element.after(nav);
                    this.showPage(this.options.activePage);
                }
                
            },
            _getNavBar: function() {
                var rows = this.options.rows;
                var nav = $('<div>', {class: 'paging-nav'});
				var noOfPages=Math.ceil(rows.length / this.options.limit);
				var startPage=0;
				var endPage=noOfPages;
				if(this.options.activePage==0 || this.options.activePage==1){
					startPage=0;
					if(noOfPages<5){
						endPage=noOfPages;
					} else {
						endPage=5;
					}
				} else if(this.options.activePage==noOfPages-1 || this.options.activePage==noOfPages-2){
					if(noOfPages<5){
						startPage=0;
					} else {
						startPage=noOfPages-5;
					}
					endPage=noOfPages;
				} else{
					startPage=this.options.activePage-2;
					endPage=this.options.activePage+3;
				}
                for (var i = startPage; i < endPage; i++) {
                    this._on($('<a>', {
                    	href: '#',
                        text: (i + 1),
                        "data-page": (i)
                    }).appendTo(nav),
                            {click: "pageClickHandler"});
                }
                //create previous link
                this._on($('<a>', {
                	href: '#',
                    text: ' << ',
                    "data-direction": -1
                }).prependTo(nav),
                        {click: "pageStepHandler"});
                //create next link
                this._on($('<a>', {
                	href: '#',
                    text: ' >> ',
                    "data-direction": +1
                }).appendTo(nav),
                        {click: "pageStepHandler"});
                //create first link
                this._on($('<a>', {
                	href: '#',
                    text: 'First',
                    "data-page": 0
                }).prependTo(nav),
                        {click: "pageClickHandler"});
                //create last link
                this._on($('<a>', {
                	href: '#',
                    text: 'Last',
                    "data-page": Math.ceil(rows.length / this.options.limit)-1
                }).appendTo(nav),
                        {click: "pageClickHandler"});
                return nav;
            },
            showPage: function(pageNum) {
                var num = pageNum * 1; //it has to be numeric
                this.options.activePage = num;
                var nav = this._getNavBar();
				$('.paging-nav').remove();
                this.element.after(nav);
				$('.paging-nav').addClass('col-md-12');
                $('.paging-nav').addClass('text-right');
                $('.paging-nav a').wrap('<li class="page-item" />');
                $('.page-item').wrapAll('<ul class="pagination"/>');
				$('.page-item').find("[data-page='" + pageNum + "']").attr('class', "in-active-pg active-bg");
                var rows = this.options.rows;
                var limit = this.options.limit;
                for (var i = 0; i < rows.length; i++) {
                    if (i >= limit * num && i < limit * (num + 1)) {
                        $(rows[i]).css('display', this.options.rowDisplayStyle);
                    } else {
                        $(rows[i]).css('display', 'none');
                    }
                }
            },
            pageClickHandler: function(event) {
                event.preventDefault();
                //$(event.target).siblings().attr('class', "");
                //$(event.target).parent().siblings().children().removeClass("in-active-pg");
                //$(event.target).attr('class', "in-active-pg");
                var pageNum = $(event.target).attr('data-page');
                this.showPage(pageNum);
            },
            pageStepHandler: function(event) {
                event.preventDefault();
                //get the direction and ensure it's numeric
                var dir = $(event.target).attr('data-direction') * 1;
                var pageNum = this.options.activePage + dir;
                //if we're in limit, trigger the requested pages link
                if (pageNum >= 0 && pageNum < this.options.rows.length) {
                    $("a[data-page=" + pageNum + "]", $(event.target).parent().parent()).click();
                }
            }
        });
    });
})(jQuery);



