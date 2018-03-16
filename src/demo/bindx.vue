<template>
    <div>
        <div class="header" >
            <image ref="image" class="headerImg" src="https://img.alicdn.com/tfs/TB1SOyrSVXXXXb3XXXXXXXXXXXX-1000-667.jpg"></image>
            <div ref="head_bar" class="head_bar">
                <text class="title">aweome binding</text>
            </div>
            <image class="circle" ref="circle" src="https://img.alicdn.com/tfs/TB1KXuHQVXXXXazXFXXXXXXXXXX-750-499.jpg">

            </image>
        </div>

        <list class="list" ref="list" >
            <cell class="blankHeader">
            </cell>

            <cell v-for="(item, index) in items" :key="item.src" class="cell" ref="index">
                <div class="item">
                    <text v-if="item.name" class="itemName">{{item.name}}</text>
                    <image class="itemPhoto" :src="item.src"></image>
                    <text v-if="item.desc" class="itemDesc">{{item.desc}}</text>
                    <text v-if="item.behaviourName" class="itemClickBehaviour"> {{item.behaviourName}}</text>
                </div>
            </cell>

        </list>
    </div>
</template>

<style scoped>

    .head_bar {
        position:absolute;
        width:750px;
        height:120px;
        background-color:#6A1B9A;
        opacity:0;
        justify-content:center;
        align-items:center;

    }

    .title {
        color: #ffffff;
        font-size:32px;

    }

    .circle {
        width:250px;
        height:250px;
        border-radius:125px;
        background-color:#ff0000;
        margin-left:250px;
        margin-top:205px;
    }

    .header {
        width:750px;
        height: 700px;
        position:absolute;
    }
    .headerImg {
        height: 700px;
        width: 750px;
        position:absolute;
    }

    .list {
        margin-top:120px;
        width: 750px;
    }
    .blankHeader {
        height:580px;
    }



    .item {
        background-color: #FFFFFF;
        align-items: center;
    }
    .itemName {
        font-size:28;
        color:#333333;
        line-height:42;
        text-align:left;
        margin-top: 24;
    }
    .itemPhoto {
        margin-top: 18;
        width: 220;
        height: 220;
        margin-bottom: 18;
    }
    .itemDesc {
        font-size:24;
        margin:12;
        color:#999999;
        line-height:36;
        text-align:left;
    }
    .itemClickBehaviour {
        font-size:36;
        color:#00cc99;
        line-height:36;
        text-align:center;
        margin-top: 6;
        margin-left: 24;
        margin-right: 24;
        margin-bottom: 30;
    }

</style>

<script>
    module.exports = {
        methods: {

        },
        created: function() {

        },
        mounted: function() {

            console.log("mounted!!!!!!");

            const binding = weex.requireModule('bindingx');
            var timer = weex.requireModule('timer');
            var self = this;
            timer.setTimeout(function(){
                var list =self.$refs.list.ref;
                var circle = self.$refs.circle.ref;
                var image = self.$refs.image.ref;
                var head_bar = self.$refs.head_bar.ref;


                var image_origin = "0-min(300,y/580*300)";
                var image_transformed = "{\"type\":\"-\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":0},{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":300},{\"type\":\"*\",\"children\":[{\"type\":\"/\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]},{\"type\":\"NumericLiteral\",\"value\":300}]}]}]}]}"

                var circle_scale_orign = "max(1-min(y,580)/580,0.4)" //1--->0.4
                var circle_scale_transformed = "{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"max\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"-\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":1},{\"type\":\"/\",\"children\":[{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":0.4}]}]}"

                var circle_translate_x_origin = "(0-min(y,580)/580)*295"//0---> -250    0 660  (0-y/660)*250
                var circle_translate_x_transformed = "{\"type\":\"*\",\"children\":[{\"type\":\"-\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":0},{\"type\":\"/\",\"children\":[{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":295}]}"

                var circle_translate_y_origin = "(0-min(y,580)/580)*268"//0---> -205  0 660   (0-y/660)*205
                var circle_translate_y_transformed = "{\"type\":\"*\",\"children\":[{\"type\":\"-\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":0},{\"type\":\"/\",\"children\":[{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":268}]}"


                var head_opacity_origin = "min(y,580)/580"//0.0-1.0  0 580
                var head_opacity_transformed = "{\"type\":\"/\",\"children\":[{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":580}]}"

                var head_background_origin = "evaluateColor('#6A1B9A','#FF9800',(y<=580?0:min(500,y-580)/500))"//#6A1B9A-->>  #CDDC39 580--> 1080
                var head_background_transformed = "{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"evaluateColor\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"StringLiteral\",\"value\":\"'#6A1B9A'\"},{\"type\":\"StringLiteral\",\"value\":\"'#FF9800'\"},{\"type\":\"?\",\"children\":[{\"type\":\"<=\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]},{\"type\":\"NumericLiteral\",\"value\":0},{\"type\":\"/\",\"children\":[{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":500},{\"type\":\"-\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]}]}]},{\"type\":\"NumericLiteral\",\"value\":500}]}]}]}]}"

                var result = binding.bind({
                    eventType:'scroll',
                    anchor:list,
                    props:[
                        {
                            element:image,
                            property:'transform.translateY',
                            expression:{
                                origin:image_origin,
                                transformed: image_transformed
                            }
                        },
                        {
                            element:circle,
                            property:'transform.scaleX',
                            expression:{
                                origin:circle_scale_orign,
                                transformed: circle_scale_transformed
                            }
                        },
                        {
                            element:circle,
                            property:'transform.scaleY',
                            expression:{
                                origin:circle_scale_orign,
                                transformed: circle_scale_transformed
                            }
                        },
                        //circle: translate_x
                        {
                            element:circle,
                            property:'transform.translateX',
                            expression:{
                                origin:circle_translate_x_origin,
                                transformed: circle_translate_x_transformed
                            }
                        },
                        //circle: translate_y
                        {
                            element:circle,
                            property:'transform.translateY',
                            expression:{
                                origin: circle_translate_y_origin,
                                transformed: circle_translate_y_transformed
                            }
                        },
                        //head: opacity
                        {
                            element:head_bar,
                            property:'opacity',
                            expression:{
                                origin: head_opacity_origin,
                                transformed: head_opacity_transformed
                            }
                        },
                        //head: background
                        {
                            element:head_bar,
                            property:'background-color',
                            expression:{
                                origin: head_background_origin,
                                transformed: head_background_transformed
                            }
                        },

                    ]
                },function(e){

                })

            },200);


        },
        data: function() {
            const items = [
                {
                    src:'https://gw.alicdn.com/tps/TB1Jl1CPFXXXXcJXXXXXXXXXXXX-370-370.jpg',
                    name: 'Thomas Carlyle',
                    desc:'Genius only means hard-working all one\'s life',
                    behaviourName: '#### NO.1',
                },
                {
                    src:'https://gw.alicdn.com/tps/TB1Hv1JPFXXXXa3XXXXXXXXXXXX-370-370.jpg',
                    desc:'The man who has made up his mind to win will never say "impossible "',
                    behaviourName: '#### NO.2',
                },
                {
                    src:'https://gw.alicdn.com/tps/TB1eNKuPFXXXXc_XpXXXXXXXXXX-370-370.jpg',
                    desc:'There is no such thing as a great talent without great will - power',
                    behaviourName: '#### NO.3',
                },
                {
                    src:'https://gw.alicdn.com/tps/TB1DCh8PFXXXXX7aXXXXXXXXXXX-370-370.jpg',
                    name:'Addison',
                    desc:'Cease to struggle and you cease to live',
                    behaviourName: '#### NO.4',
                },
                {
                    src:'https://gw.alicdn.com/tps/TB1ACygPFXXXXXwXVXXXXXXXXXX-370-370.jpg',
                    desc:'A strong man will struggle with the storms of fate',
                    behaviourName: '#### NO.5',
                },
                {
                    src:'https://gw.alicdn.com/tps/TB1IGShPFXXXXaqXVXXXXXXXXXX-370-370.jpg',
                    name:'Ruskin',
                    desc:'Living without an aim is like sailing without a compass',
                    behaviourName: '#### NO.6',
                },
                {
                    src:'https://gw.alicdn.com/tps/TB1xU93PFXXXXXHaXXXXXXXXXXX-240-240.jpg',
                    behaviourName: '#### NO.7',
                },
                {
                    src:'https://gw.alicdn.com/tps/TB19hu0PFXXXXaXaXXXXXXXXXXX-240-240.jpg',
                    name:'Balzac',
                    desc:'There is no such thing as a great talent without great will - power',
                    behaviourName: '#### NO.8',
                },
                {
                    src:'https://gw.alicdn.com/tps/TB1ux2vPFXXXXbkXXXXXXXXXXXX-240-240.jpg',
                    behaviourName: '#### NO.9',
                },
                {
                    src:'https://gw.alicdn.com/tps/TB1tCCWPFXXXXa7aXXXXXXXXXXX-240-240.jpg',
                    behaviourName: '#### NO.10',
                }
            ]

            let repeatItems = [];
            for (let i = 0; i < 3; i++) {
                repeatItems.push(...items)
            }

            return {

                items: repeatItems
            }
        },
    }
</script>
