# HfutOpenApi

这是一个基于合肥工业大学教务系统网页与APP的爬虫

提供了一个相对友好的方式获取学生个人的成绩,课表,等JSON数据以及公开数据

# 免责说明
本库遵循MIT协议进行开源

HfutOpenApi提供的内容仅用于个人学习、研究或欣赏。

我们不保证内容的正确性。通过使用本库代码随之而来的风险与作者无关

使用者可将提供的代码用于个人学习、研究或欣赏，以及其他非商业性或非盈利性用途，

但同时应遵守著作权法及其他相关法律的规定，不得侵犯相关权利人的合法权利。

本库不提供任何形式的保证。

所有与使用本库相关的直接风险均由用户承担。

本库提供的所有代码均为实例，并不对性能、适用性、适销性或/及其他方面提供任何保证

# 文档

### 初始化

```java
public class Demo {
    public static void main(String[] args) {
        Student s = new Student("your number","your password");
        Credential c = s.login();
    }
}
```

通过创建Student对象,表示应用一个学生对象.调用其login方法,获得一个凭证类.

凭证的过期时间在5分钟,过期了之后,可以使用Credential的update方法,传入同一个Student对象来对凭证进行更新

### 请求

目前这个库实现了获取:

- 个人信息
- 培养计划的课程
- 课程表
- 学期信息

#### 个人信息

包括以下字段

1. 中文名
2. 英文名
3. 性别
4. 证件类型
5. 证件号
6. 年级
7. 教育程度
8. 学生类型
9. 学院
10. 专业
11. 校区
12. 注册日期
13. 预期毕业日期
14. 生源省份

初始化获得了凭证后,可以根据凭证,运行以下代码获得学生个人信息

```java
StudentInfo info = StudentInfoService.getBasicStudentInfo(credential);
```

#### 学期信息

包括以下字段:

1. 学期名称
2. 学期总周数
3. 学期开始日期
4. 学期结束日期
5. 当前学年
6. 当前学期(用1表示上学期,2表示下学期)

初始化获得了凭证后,可以根据凭证,运行以下代码获得学期信息

```java
List<Semester> semesterList = SemesterService.getSemesterList(credential);
```

#### 培养计划

包括以下字段

1. 通识教育必修课课程
2. 通识教育必修课学分
3. 专业必修课课程
4. 专业必修课学分
5. 专业选修课课程
6. 专业选修课学分
7. 实践课程
8. 实践学分

初始化获得了凭证后,可以根据凭证,运行以下代码获得培养计划信息

```java
Program program = ProgramService.getProgram(credential);
```

