export default {
  getFullPath(){
    return document.location.href
  },
  getRoot(){
    let full=this.getFullPath()
    let spe='index.html#'
    return full.substr(0,full.indexOf(spe)+spe.length)
  },
  getVuePath(){
    return this.getFullPath().replace(this.getRoot(),'')
  },
  getRelativeVuePath(path){
    return  this.getRelativePath(path).replace(this.getRoot(),'')
  },
  getRelativePath(path)
  {
    let url=path
    let base=this.getFullPath()
    let q=url.split("../");
    let x= base.split("/");
    let p="";
    for(let i=0;i<x.length-q.length;i++)
    {
      p+=x[i]+"/";
    }
    p+=q[q.length-1];
    return p;
  },
  getPath(url){
    if(url.startsWith('http')){
      return url
    }
    if(url.startsWith('root:')){
      return url.replace('root:','/')
    }
    url= this.getRelativeVuePath(url)
    if(url.indexOf('#')!=-1){
      url=url.split('#')[1]
    }
    return url
  },
  getImagePath(url){
    if(url.startsWith('http')){
      return url
    }
    if(url.startsWith('root:')){
      return url.replace('root:',this.getRoot()+"/")
    }
    return this.getRelativePath(url)
  }



}