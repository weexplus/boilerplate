#!/usr/bin/env node
const child_process = require('child_process')
 let projectInfoText=child_process.execSync('xcodebuild  -list', {encoding: 'utf8'});
    console.log('klklklkl======'+projectInfoText+"==========")
    let splits=projectInfoText.split(/Targets:|Build Configurations:/);


    
    let projectInfo={};
    
    projectInfo.name=splits[0].match(/Information about project "([^"]+?)"/)[1];
    projectInfo.targets=splits[1]?splits[1].split('\n').filter(e=>!!e.trim()).map(e=>e.trim()):[];
    projectInfo.configurations=splits[2]?splits[2].split('\n').filter((e,i)=>!!e.trim()&&i<3).map(e=>e.trim()):[];
    projectInfo.schemes=splits[3]?splits[3].split('\n').filter(e=>!!e.trim()).map(e=>e.trim()):[];
    for(var i=0;i<splits.length;i++)
    {
      console.log('xxxx======'+ splits[i]+"==========xxxx")
    }