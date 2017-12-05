<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>
    Zoomable Chart Example - HTML5 jQuery Chart Plugin by jqChart
</title>
      <link rel="stylesheet" type="text/css" href="../../lib/jqChart/css/jquery.jqChart.css" />
    <link rel="stylesheet" type="text/css" href="../../lib/jqChart/css/jquery.jqRangeSlider.css" />
    <link rel="stylesheet" type="text/css" href="../../lib/jqChart/themes/smoothness/jquery-ui-1.8.21.css" />
    <script src="../../lib/jqChart/js/jquery.mousewheel.js" type="text/javascript"></script>
    <script src="../../lib/jqChart/js/jquery.jqChart.min.js" type="text/javascript"></script>
    <script src="../../lib/jqChart/js/jquery.jqRangeSlider.min.js" type="text/javascript"></script>
    <!--[if IE]><script lang="javascript" type="text/javascript" src="../js/excanvas.js"></script><![endif]-->
    
    <script lang="javascript" type="text/javascript">
        function addDays(date, value) {
            var newDate = new Date(date.getTime());
            newDate.setDate(date.getDate() + value);
            return newDate;
        }

        function round(d) {
            return Math.round(100 * d) / 100;
        }

        var data1 = [];
        var data2 = [];

        var yValue1 = 50;
        var yValue2 = 200;

        var date = new Date(2010, 0, 1);

        for (var i = 0; i < 200; i++) {

            yValue1 += Math.random() * 10 - 5;
            data1.push([date, round(yValue1)]);

            yValue2 += Math.random() * 10 - 5;
            data2.push([date, round(yValue2)]);

            date = addDays(date, 1);
        }

        $(document).ready(function () {

            var background = {
                type: 'linearGradient',
                x0: 0,
                y0: 0,
                x1: 0,
                y1: 1,
                colorStops: [{ offset: 0, color: '#d2e6c9' },
                             { offset: 1, color: 'white' }]
            };

            $('#jqChart').jqChart({
                title: 'Chart Title',
                legend: { title: 'Legend' },
                border: { strokeStyle: '#6ba851' },
                background: background,
                animation: { duration: 1 },
                tooltips: { type: 'shared' },
                shadows: {
                    enabled: true
                },
                crosshairs: {
                    enabled: true,
                    hLine: false,
                    vLine: { strokeStyle: '#cc0a0c' }
                },
                axes: [
                    {
                        type: 'dateTime',
                        location: 'bottom',
                        zoomEnabled: true
                    }
                ],
                series: [
                    {
                        title: 'Series 1',
                        type: 'line',
                        data: data1,
                        markers: null
                    },
                    {
                        title: 'Series 2',
                        type: 'line',
                        data: data2,
                        markers: null
                    }
                ]
            });

            $('#jqChart').bind('tooltipFormat', function (e, data) {

                if ($.isArray(data) == false) {

                    var date = data.chart.stringFormat(data.x, "ddd, mmm dS, yyyy");

                    var tooltip = '<b>' + date + '</b><br />' +
                          '<span style="color:' + data.series.fillStyle + '">' + data.series.title + ': </span>' +
                          '<b>' + data.y + '</b><br />';

                    return tooltip;
                }

                var date = data[0].chart.stringFormat(data[0].x, "ddd, mmm dS, yyyy");

                var tooltip = '<b>' + date + '</b><br />' +
                      '<span style="color:' + data[0].series.fillStyle + '">' + data[0].series.title + ': </span>' +
                      '<b>' + data[0].y + '</b><br />' +
                      '<span style="color:' + data[1].series.fillStyle + '">' + data[1].series.title + ': </span>' +
                      '<b>' + data[1].y + '</b><br />';

                return tooltip;
            });
        });
    </script>

</head>
<body>
    <div>
        <div id="jqChart" style="width: 500px; height: 300px;">
        </div>
    </div>
</body>
</html>


