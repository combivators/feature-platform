## Feature Platform: 一个基于Tiny Service微服框架构筑的具有AI功能的未来型人才信息平台
## 设计目的
 - 用于验证各项子系统核心服务机能。
 - 加权评估引擎。
 - 对数值化后的资历表项目进行3类6种评价计算，并输出JSON格式评估结果。
 - 输出SVG格式的评价结果雷达图。
 - 动态输出匹配结果3D云球效果图。
 - 评估策略参数需由外部提供，并能够实时更新和个别调整。
 - 提供上述服务的相应在线API。

##Usage

###1. 数值化资历评估API
```console
url -X POST -H 'Content-Type:application/json' -H "Accept: application/json" http://localhost:8080/v1/api/assess/1/scores -d @src/test/resources/data/qualifications-sample.json
```


###2. 评价结果SVG格式雷达图API
```console
curl http://localhost:8080/v1/chart/svg/CwoqKr8hmIy2QxOHU8euCBU0M7jA77JkTBchMW
```


##API用JSON
###1. 项目集合评价策略
```json
{
  "ver" : "1.0",
  "id" : "1110",
  "types" : ["Skill","Learning","Management","Productivity","Growing","Compatibility"],
  "tables" : [
      {
      "title" : "Id",
      "weights" : [0.0,0.0,0.0,0.0,0.0,0.0]
    },
      {
      "title" : "Sex",
      "weights" : [0.01,0.02,0.03,0.04,0.05,0.06]
    },
      {
      "title" : "Age",
      "weights" : [0.02,0.02,0.02,0.02,0.02,0.02]
    },
      {
      "title" : "Nationality",
      "weights" : [0.02,0.02,0.02,0.02,0.02,0.02]
    },
      {
      "title" : "Civil",
      "weights" : [0.03,0.03,0.03,0.03,0.03,0.03]
    },
      {
      "title" : "Visa",
      "weights" : [0.04,0.04,0.04,0.04,0.04,0.04]
    },
      {
      "title" : "Education",
      "weights" : [0.05,0.05,0.05,0.05,0.05,0.05]
    },
      {
      "title" : "Specialty",
      "weights" : [0.06,0.06,0.06,0.06,0.06,0.06]
    },
      {
      "title" : "Graduation",
      "weights" : [0.07,0.07,0.07,0.07,0.07,0.07]
    },
      {
      "title" : "Income",
      "weights" : [0.08,0.08,0.08,0.08,0.08,0.08]
    },
      {
      "title" : "Certification",
      "weights" : [0.09,0.09,0.09,0.09,0.09,0.09]
    },
      {
      "title" : "Years",
      "weights" : [0.10,0.10,0.10,0.10,0.10,0.10]
    },
      {
      "title" : "Changes",
      "weights" : [0.11,0.11,0.11,0.11,0.11,0.11]
    },
      {
      "title" : "Career",
      "weights" : [0.12,0.12,0.12,0.12,0.12,0.12]
    },
      {
      "title" : "Experience",
      "weights" : [0.13,0.13,0.13,0.13,0.13,0.13]
    },
      {
      "title" : "Duties",
      "weights" : [0.14,0.14,0.14,0.14,0.14,0.14]
    },
      {
      "title" : "Field",
      "weights" : [0.15,0.15,0.15,0.15,0.15,0.15]
    },
      {
      "title" : "Language",
      "weights" : [0.11,0.12,0.13,0.14,0.15,0.16]
    }
  ],
  "modified" : 1566284699151,
  "expires" : 1566286427151,
  "signature" : "Hoge"
}
```

###2. 评价计算的资历表
```json
{
  "ver" : "1.0",
  "id" : "987",
  "titles" : ["Id","Sex","Age","Nationality","Civil","Visa","Education","Specialty","Graduation","Income","Certification","Years","Changes","Career","Experience","Duties","Field","Language"],
  "scores" : [0.0,51.0,52.0,53.0,54.0,55.0,56.0,57.0,58.0,59.0,60.0,61.0,62.0,63.0,64.0,65.0,67.0,68.0],
  "modified" : 1566358104190,
  "signature" : "Fuga"
}

```

###3. 评估结果数值表
```json
{
  "ver" : "1.0",
  "form" : "1110",
  "assets" : "987",
  "scores" : [
      {
      "type" : "Skill",
      "value" : 82.26
    },
      {
      "type" : "Learning",
      "value" : 83.45
    },
      {
      "type" : "Management",
      "value" : 84.64
    },
      {
      "type" : "Productivity",
      "value" : 85.83
    },
      {
      "type" : "Growing",
      "value" : 87.02
    },
      {
      "type" : "Compatibility",
      "value" : 88.21
    }
  ],
  "modified" : 1566371163739,
  "signature" : "Hoge:Fuga"
}

```

##More Detail, See The Samples


---
Email   : wuweibg@gmail.com
