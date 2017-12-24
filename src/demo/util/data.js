
export default {

       getClassData:function()
       {

           var pref=weex.requireModule("pref");
           var s=pref.get('serial_class');
           var modal=weex.requireModule("modal")
           // modal.alert({message:"l"+s+"l"})
           if(s!=undefined&&s!='')
           {
               return s;
           }



           var l=   {"msg":"成功","err":0,"list":[{"name":"魔幻","id":5},{"name":"青春","id":6},{"name":"都市","id":7},{"name":"迷你剧","id":8},{"name":"谍战","id":9},{"name":"记录","id":10},{"name":"西部","id":11},{"name":"血腥","id":12},{"name":"罪案","id":13},{"name":"综艺","id":14},{"name":"科幻","id":15},{"name":"真人秀","id":16},{"name":"爱情","id":17},{"name":"歌舞","id":18},{"name":"暴力","id":19},{"name":"政治","id":20},{"name":"战争","id":21},{"name":"惊悚","id":22},{"name":"悬疑","id":23},{"name":"律政","id":24},{"name":"家庭","id":25},{"name":"奇幻","id":26},{"name":"喜剧","id":27},{"name":"吸血鬼","id":28},{"name":"同性","id":29},{"name":"史诗","id":30},{"name":"古装","id":31},{"name":"历史","id":32},{"name":"医务","id":33},{"name":"动画","id":34},{"name":"动作","id":35},{"name":"剧情","id":36},{"name":"冒险","id":37},{"name":"传记","id":38},{"name":"丧尸","id":39},{"name":"情景喜剧","id":40}]};
           return l.list;
       },
    getMovieClassData:function()
    {

        var pref=weex.requireModule("pref");
        var s=pref.get('movie_class');
        var modal=weex.requireModule("modal")
        // modal.alert({message:"l"+s+"l"})
        if(s!=undefined&&s!='')
        {
            return s;
        }

         var l={"msg":"成功","err":0,"list":[{"name":"动作","id":41},{"name":"剧情","id":42},{"name":"悬疑","id":43},{"name":"喜剧","id":44},{"name":"爱情","id":45},{"name":"战争","id":46},{"name":"科幻","id":47},{"name":"灾难","id":48},{"name":"恐怖","id":49},{"name":"犯罪","id":50},{"name":"动漫","id":51},{"name":"惊悚","id":52},{"name":"奇幻","id":53},{"name":"冒险","id":54},{"name":"动作","id":41},{"name":"剧情","id":42},{"name":"悬疑","id":43},{"name":"喜剧","id":44},{"name":"爱情","id":45},{"name":"战争","id":46},{"name":"科幻","id":47},{"name":"灾难","id":48},{"name":"恐怖","id":49},{"name":"犯罪","id":50},{"name":"动漫","id":51},{"name":"惊悚","id":52},{"name":"奇幻","id":53},{"name":"冒险","id":54}]}

        return l.list;
    },






}
