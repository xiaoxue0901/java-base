<h1>设计模式说明</h1>
---
`代码地址:com.autumn.demo.javabase.designpattern包下存放的是23中设计模式的代码示例 `

<h2>07-Builder(建造者)模式</h2>
`说明: Builder模式-组装复杂的实例`
`地址:demo07_builder`

```$xslt
Builder(建造者)
Builder角色负责定义用于生成实例的接口(API), Builder角色中准备了用于生成实例的方法.
```

```$xslt
ConcreteBuilder(具体的建造者)
ConcreteBuilder角色是负责实现Builder角色的接口的类, 这里定义了在生成实例时实际被调用的方法,
此外,在ConcreteBuilder角色中还定义了最终生成结果的方法
```
```$xslt
Director(监工) 
Director角色负责使用Builder角色的接口来生成实例, 不依赖concreteBuilder角色.为了确保不论ConcreteBuilder
角色是如何被定义的, Director角色都能正常工作, 它只调用在Builder角色中被定义的方法.
```



