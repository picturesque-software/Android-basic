# 移动端开发（Android

## 1. 安卓开发基础

- AndroidManifest.xml：项目的主要信息（图标，语言，主activity等

- app的唯一标识：app id=application id+开发者签名

- application id是表明这个app在开发组织下的表示，通常为开发组织的域名倒排，例如：

  ```
  com.bytedance.android.exampleapp
  ```

  application id一般声明在build.gradle文件中

- 开发者签名可以在本地创建，也可以在谷歌服务器上生成签名，将app上传到google play console自动帮我们签名（要收费

- 遇到gradleimport特别慢的情况，要换源（build.gradle里）

- 安卓开发四大组件：

  - activity
  - service
  - content provider：使**一个应用程序的指定数据集提供给其他应用程序**。其他应用可以通过ContentResolver类从该内容提供者中获取或存入数据。只有需要在多个应用程序间共享数据是才需要内容提供者。例如，通讯录数据被多个应用程序使用，且必须存储在一个内容提供者中。它的好处是统一数据访问方式。
  - broadcast receiver：你的应用可以使用它对外部事件进行过滤，只对感兴趣的外部事件(如当电话呼入时，或者数据网络可用时)进行接收并做出响应。广播接收器没有用户界面。

### 1.1 Activity

- Activity 是 android 的关键组件，表示一个界面，之所以称为“活动”是因为它承载了很多的交互逻辑。
- 所有的activity都要注册到AndroidManifest.xml里
- 默认启动的activity要加标签：

![image-20220412152544327](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412152544327.png)

- 建好后AS会默认帮我们配置，有时候会加上属性：

![image-20220412153614453](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412153614453.png)

android:exported="false"，表示是否支持其他应用调用

activity之间的跳转：

![image-20220412153804149](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412153804149.png)

定义一个button，通过id来控制这个button

![image-20220412154110680](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412154110680.png)

- 显式跳转与隐式跳转

![image-20220412154337362](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412154337362.png)

- 显式跳转

![image-20220412154426169](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412154426169.png)

- 隐式跳转

![image-20220412154640646](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412154640646.png)

![image-20220412154649456](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412154649456.png)

- activity的七大生命周期

![image-20220412155342734](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412155342734.png)

- onCreate():创建这个页面

  您必须实现此回调，它会在系统创建您的 Activity 时触发。您的实现应该初始化 Activity 的基本组件：例如，您的应用应该在此处创建视图并将数据绑定到列表。最重要的是，您必须在此处调用 setContentView() 来定义 Activity 界面的布局。

  onCreate()一般会带一个参数：

  ![image-20220412155719998](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412155719998.png)

  这个参数用于保存activity的实例状态，这里表示创建的时候恢复状态？（不确定

- onStart()：这个页面可见了

  activity将进入“已启动”状态，并对用户可见。此回调包含 Activity 进入前台与用户进行互动之前的最后准备工作。

- onResume()：这个页面可交互了

  系统会在 Activity **开始与用户互动之前**调用此回调。此时，该 Activity 位于 Activity 堆栈的顶部，并会捕获所有用户输入。应用的大部分核心功能都是在 onResume() 方法中实现的。

经过以上三个步骤，activity进入running状态。

接下来，如果按返回键或者点击桌面一些情况：

- onPause()：不可交互了

  当 Activity 失去焦点并进入“已暂停”状态时，系统就会调用 onPause()。例如，当用户点按“返回”或“最近使用的应用”按钮时，就会出现此状态。当系统为您的 Activity 调用 onPause() 时，从技术上来说，这意味着您的 Activity 仍然部分可见，但大多数情况下，这表明用户正在离开该 Activity，该 Activity 很快将进入“已停止”或“已恢复”状态。

  如果用户希望界面继续更新，则处于“已暂停”状态的 Activity 也可以继续更新界面。例如，显示导航地图屏幕或播放媒体播放器的 Activity 就属于此类 Activity。即使此类 Activity 失去了焦点，用户仍希望其界面继续更新。

- onStop()：不可见了

  当 Activity 对用户不再可见时，系统会调用 onStop()。出现这种情况的原因可能是 Activity 被销毁，新的 Activity 启动，或者现有的 Activity 正在进入“已恢复”状态并覆盖了已停止的 Activity。在所有这些情况下，停止的 Activity 都将完全不再可见。

- onDestroy()：销毁了

  系统会在销毁 Activity 之前调用此回调。

  此回调是 Activity 接收的最后一个回调。通常，实现 onDestroy() 是为了确保在销毁 Activity 或包含该 Activity 的进程时释放该 Activity 的所有资源。

当点击home时，会onStop，而不会onDestroy（可能长时间不操作后os会帮我们销毁）。在有限的时间里再次点击这个应用，将会执行onRestart

- onRestart()

  当处于“已停止”状态的 Activity 即将重启时，系统就会调用此回调。onRestart() 会从 Activity 停止时的状态恢复 Activity。

  此回调后面总是跟着 onStart()。

左边有一条路径是说系统的内存不足时，当前的activity可能会被系统优先级更高的activity剥夺资源。从而下次需要使用时，重新onCreate





- 从一个activity跳转到另一个activity，两个activity的生命周期会怎么演化？

  A--->B：

  - A不可交互onParse()
  - B创建 onCreate()
  - B可见 onStart
  - B可交互 onResume
  - A不可见 onStop



- activity之间的数据传递

![image-20220412162715652](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412162715652.png)

![image-20220412162736805](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412162736805.png)

![image-20220412162834542](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412162834542.png)

- activity与进程

  一个app通常有多个进程，一个进程可以有多个activity





- activity的四种启动方式：

  可以在AndroidManifest.xml中修改启动方式：

  ```xml
  <activity android:name=".MainActivity"
              android:launchMode="standard">
      <intent-filter>
          <action android:name="android.intent.action.MAIN" />
  
          <category android:name="android.intent.category.LAUNCHER" />
      </intent-filter>
  </activity>
  
  ```

  - standard：默认的启动方式，当启动一个activity时，就会将该activity放入返回栈栈顶，无论返回栈中是否已有该Activity。

  - singleTop：相较于标准模式更进了一步，在standard模式中，你可能会觉得明明这个Activity已经在返回栈的栈顶了为什么还要再建立一个新的呢？
    在这种情况下我们就可以使用singleTop模式。

    当我们在启动一个新的activity的时候，系统会进行判断该activity是否处于返回栈的栈顶，如果是，则启动该activity，如果不是则启动一个新的activity。

  - singleTask：singleTask 模式在singleTop的基础上更进了一步。singleTop只能判断栈顶 ，而未处于栈顶的activity 仍然会选择新建一个新的activity，而singleTask则解决了这个问题：当启动一个新的activity的时候，系统会在整个返回栈中进行查找，是否有该activity，如果有则**出栈在这个activity之上的其他activity**，以此启动该activity。

  - singleInstance：singleInstance模式是4种模式中最为复杂的模式。当我们启动一个新的activity的时候，它会启动一个**新的返回栈**来管理这个activity。

    这么做的意义在于：
    想象以下场景，假设我们的程序中有一个活动是允许其他程序调用的，如果我们想实现其他程序和我们的程序可以共享这个活动的实例，应该如何实现呢?使用前面3种启动模式肯定是做不到的，因为每个应用程序都会有自己的返回栈，同一个活动在不同的返回栈中人栈时必然是创建了新的实例。而使用singleInstance模式就可以解决这个问题，在这种模式下会有一个单独的返回栈来管理这个活动，不管是哪个应用程序来访问这个活动，都共用的同一个返回栈，也就解决了共享活动实例的问题。



### 1.2 UI Framework



![image-20220412163632086](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412163632086.png)

![image-20220412163603839](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412163603839.png)

#### 1.2.1 View

![image-20220412163838535](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412163838535.png)

- 用像素点来定义view的尺寸非常不科学，因为不同的机器像素不一样，其显示大小也不一样，像素密度千差万别。

![image-20220412164035174](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412164035174.png)

![image-20220412164604762](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412164604762.png)

- 通过实际设备的大小来进行等比例放大

![image-20220412164819089](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412164819089.png)

![image-20220412164829454](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412164829454.png)

![image-20220412164843564](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412164843564.png)

- view的各种属性：

![image-20220412164942953](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412164942953.png)

![image-20220412165231007](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412165231007.png)

![image-20220412172538614](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412172538614.png)

![image-20220412172600486](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412172600486.png)

注意，View是可以运行时动态修改的！

下面是几个具体的View类型

##### 1.2.1.1 TextView

![image-20220412172719500](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412172719500.png)

- 不能直接硬编码文本，而是要在res文件夹下建立values，values-zh等文件夹，在文件夹中定义string.xml，在其中定义具体显示的字符串。在使用的时候直接@string/字符串名

![image-20220412172912788](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412172912788.png)

![image-20220412172925036](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412172925036.png)

- 注意这个目录

![image-20220412172951715](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412172951715.png)

![image-20220412173013165](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412173013165.png)



![image-20220412173027934](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412173027934.png)



![image-20220412173232484](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412173232484.png)

![image-20220412173245169](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412173245169.png)



##### 1.2.1.2 Button

![image-20220412173703707](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412173703707.png)

##### 1.2.1.3 ImageView

![image-20220412173739676](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412173739676.png)

##### 1.2.1.4 EditText

![image-20220412194400991](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412194400991.png)

![image-20220412194413400](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412194413400.png)

![image-20220412194446580](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412194446580.png)

##### 1.2.1.5 Toast

![image-20220412194534048](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412194534048.png)

![image-20220412194611726](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412194611726.png)

##### 1.2.1.6 ProgressBar

![image-20220412194642164](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412194642164.png)

![image-20220412194729265](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412194729265.png)

![image-20220412194756001](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412194756001.png)

![image-20220412194806162](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412194806162.png)

![image-20220412194841720](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412194841720.png)





#### 1.2.2 Layout

![image-20220412165542856](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412165542856.png)

![image-20220412165558550](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412165558550.png)

区别在于内部的View是如何摆放的

![image-20220412165922489](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412165922489.png)

![image-20220412170248282](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412170248282.png)

![image-20220412170257755](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412170257755.png)

![image-20220412170434326](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412170434326.png)

- 通过layout的view的全局唯一性来获取view对象并进行操作

![image-20220412170554717](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412170554717.png)

##### 1.2.2.1 frame Layout

![image-20220412170707427](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412170707427.png)

![image-20220412170725073](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412170725073.png)

- 这个一般来做根部局

##### 1.2.2.2 linear Layout

![image-20220412171215412](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412171215412.png)

![image-20220412171234215](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412171234215.png)

![image-20220412171247238](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412171247238.png)

![image-20220412171300336](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412171300336.png)

![image-20220412171334578](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412171334578.png)

- 权重分布：

![image-20220412171342111](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412171342111.png)

![image-20220412171350931](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412171350931.png)

![image-20220412171358089](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412171358089.png)

##### 1.2.2.3 relative Layout

![image-20220412171723578](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412171723578.png)

![image-20220412172033044](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412172033044.png)

![image-20220412172042918](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412172042918.png)

![image-20220412172050175](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412172050175.png)

![image-20220412172104213](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412172104213.png)

- 除了我们介绍的 3 中经典布局之外，Android 还为开发者提供了更加强大的新型布局 **constrant layout**，这种布局采用无嵌套设计，大大增强了界面表达能力，并且提升在构建复杂界面的性能。

  3 种经典布局是 android 操作系统本身就提供的，constraint layout 则不一样，它是由独立的 androidx library 来提供的，需要开发者手动引入。



## 2. Fragment 与 Animation

- why Fragment？ 在activity里进行页面小切换，避免了多个activity过分占用资源的情况。相当于一个mini activity，一般情况下一个app不会有很多个activity。
  - Activity模块化
  - 相比View，带有生命周期管理
  - 可重用，灵活
  - 可以动态添加和删除
- android 3.0就出现了，为了UI的重用以及不同设备的兼容性，实现了响应式设计的理念

#### 2.1 how to user Fragment？

![image-20220412200049914](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412200049914.png)

向右滑动的时候，显示另一个Fragment

通过ViewPager将两个fragment连接起来



![image-20220412200204085](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412200204085.png)

- 底层最终的实现还是View

- 依赖于activity，有点像其他View嵌入的方法

![image-20220412200444118](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412200444118.png)

![image-20220412200510555](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412200510555.png)

- 通过fragmentManager以及FragmentTransaction来动态地添加fragment

![image-20220412200603292](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412200603292.png)

![image-20220412200644148](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412200644148.png)

在一个activity里，先是**列表页，然后点击进入详情页**，这是两个Fragment，那么为了实现返回页面不出错，可以用栈实现。







![image-20220412201321444](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412201321444.png)

![image-20220412201540682](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412201540682.png)

![image-20220412201754192](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412201754192.png)

- 创建fragment的时候写定一些参数，然后onCreate的时候拿出来用就行
- 下面是activity和fragment之间的动态传参的过程

![image-20220412201923170](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412201923170.png)





- 例子：

![image-20220412202037114](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412202037114.png)



- 最关键的代码：

![image-20220412202143587](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412202143587.png)



横竖屏有两个布局文件

![image-20220412202301579](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412202301579.png)

![image-20220412202455005](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412202455005.png)

![image-20220412202515974](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412202515974.png)

#### 2.2 animation

动画：类似游戏加载过程中的动画

使用Property Animation: android.animation

- 透明度

![image-20220412203110287](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412203110287.png)

在res的anim文件夹新建动画xml布局文件，并且新建Animator，这里设置了1000ms的渐淡。然后在activity中绑定View进行播放。



- 动画的平移

![image-20220412203611418](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412203611418.png)

- 放大缩小

![image-20220412203716790](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412203716790.png)

- 动画还可以做结合一起播放等。

- 三个基本类：

![image-20220412203806145](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412203806145.png)



- animation的监听，结束的时候就把动画消除掉

![image-20220412203956501](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412203956501.png)

- 开源动画库：lottie：通过解析一个json文件来绘制动画，可远程获取json文件
- 列表页展示的工具：RecyclerView

![image-20220412204633717](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412204633717.png)

## 3. 多线程、Handler、自定义View

- 进程与线程

![image-20220412205557617](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412205557617.png)

![image-20220412205845526](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412205845526.png)

- 每个线程需要1M的空间

![image-20220412210045845](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412210045845.png)

- 绘制界面，处理用户操作的线程，安卓的主线程
  - 系统事件：
  - 输入事件：点击，长按等
  - Service
  - Alarm
  - UI Drawing



#### 3.2 Handler

任务调度与线程通信

线程交互：比如下载线程执行完毕后会给主线程发消息

当前线程产生新消息后，会把消息放到要发送给的线程的消息队列中

handler：处理消息和发送消息

![image-20220412211055369](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412211055369.png)

![image-20220412211122891](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412211122891.png)



当我们重新创建一个activity的时候，会给UI线程发消息到消息队列中

![image-20220412211403568](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412211403568.png)

当出现ANR（app no response）：是UI线程堆积的消息太多了，你的点击消息一直得不到处理。会出现无响应的提示信息。一些耗时的操作尽量不放在主线程中执行。

子线程会做网络请求，数据加载等操作。一般会将某个项目的所有子线程都放到线程池中管理。所以团队开发者不要轻易new Thread。

![image-20220412212224531](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412212224531.png)

![image-20220412212311005](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412212311005.png)

要把之前发送的消息删掉，要不然goMainActivity就两次了。。

![image-20220412212517011](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412212517011.png)

- 多进程：互相不影响，游戏和录屏

![image-20220412214107975](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412214107975.png)

- Runnable：创建线程的另外一种方法，声明实现了Runnable接口的类，然后实现run方法，再创建出Runnable的子类对象，传入某个线程的构造方法中，开启线程。这样做的好处：
  - 避免了单继承的局限性，更符合面向对象思想；线程分为两部分：线程对象（Thread和线程任务（Runnable，对线程对象和线程任务进行了解耦![image-20220412214147476](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412214147476.png)

![image-20220412214226354](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412214226354.png)

#### 3.3 多线程



![image-20220412214529976](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412214529976.png)

- 一般都用线程池来做~
- 实际是java的线程池，一般是java 1.8

![image-20220412214558225](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412214558225.png)

![image-20220412214638416](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412214638416.png)

![image-20220412214752507](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412214752507.png)

异步操作（适用于下载等场景：

![image-20220412214925551](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412214925551.png)

![image-20220412214937740](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412214937740.png)

![image-20220412215057685](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412215057685.png)

#### 3.4 Service

- 后台执行的一些东西。

![image-20220412215304311](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412215304311.png)

![image-20220412215321063](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412215321063.png)

![image-20220412215613968](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412215613968.png)

![image-20220412215623973](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412215623973.png)

#### 3.5 自定义View

![image-20220412220306002](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412220306002.png)

绘制的方法是通过Canvas

![image-20220412220413742](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412220413742.png)

一个树状结构：

![image-20220412220650734](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412220650734.png)

- ViewGroup的渲染过程：

measure，layout，draw。三个阶段都是树的一个遍历处理（对于ViewGroup还要递归处理）

重要函数：invalidate（如果布局没变化，只触发draw） requestLayout



![image-20220412221212114](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412221212114.png)

## 4. 安卓构建

![image-20220412221658764](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412221658764.png)

源工程主要有三部分：代码，资源，配置

![image-20220412221932061](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412221932061.png)

![image-20220412222119207](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412222119207.png)

基于grovvy做配置文件，会编译执行，能实现动态的配置

构建流程分成很多task，基于task可以做不同task的并发

plugin可以传播给别人来构建项目

![image-20220412222809884](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412222809884.png)

![image-20220412223328782](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412223328782.png)

![image-20220412223534261](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412223534261.png)

- setting对象：主要是一些工程的配置
- build.gradle

![image-20220412223902858](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412223902858.png)

#### 4.1 安卓构建的步骤

##### 4.1.1 资源构建：AAPT工具

![image-20220412224712282](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412224712282.png)

- 资源的编译和链接：把文本的xml文件编译成二进制的xml，提高效率

注意以下产物R.java和ARSC的区别

##### 4.1.2 代码

![image-20220412225234287](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412225234287.png)

移动端把所有的JavaClass转化成dex，在安卓手机的art虚拟机（jvm的一种）上运行。dex的空间更优，

![image-20220412225703018](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412225703018.png)

ARSC和R.java将资源和代码串联起来

## 5. 安卓网络

![image-20220412230857087](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412230857087.png)

使用安卓网络前：

首先在AndroidManifest.xml中

![image-20220412231705143](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412231705143.png)

#### 5.1 安卓原生的网络请求

![image-20220412231719174](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412231719174.png)

![image-20220412231729525](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412231729525.png)



很费劲

#### 5.1 Retrofit

![image-20220412232007363](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220412232007363.png)

## 6. 安卓存储

#### 6.1 存储概览

- 存储的物理介质：Device Storage（如本地磁盘）和Portable Storage（如SD卡）
- 存储的逻辑分区：Internal Storage，External Storage

![image-20220413170616344](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413170616344.png)

#### ![image-20220413170629543](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413170629543.png)

![image-20220413170649733](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413170649733.png)

![image-20220413170706612](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413170706612.png)

#### 6.2 文件访问

- 访问internal和external private：
- internal Storage的文件目录是/data/data/应用程序包名，需要root权限才能访问，一般用户访问不到。shared preferences就存在这里面。

![image-20220413171104860](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413171104860.png)

- 访问external public：

![image-20220413171603595](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413171603595.png)

下载视频/图片到用户的手机上，就是下载到external public。

设备的唯一id编号

![image-20220413171617152](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413171617152.png)



#### 6.3 shared preferences

- 解决一些小量的信息存储，例如键值对，用户的配置信息等

![image-20220413172351957](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413172351957.png)

![image-20220413172508235](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413172508235.png)

- 指定如果从shared preferences中拿不到值得话，取的默认值是多少

![image-20220413172528984](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413172528984.png)

![image-20220413172658418](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413172658418.png)

![image-20220413172730339](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413172730339.png)

![image-20220413173500443](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413173500443.png)

#### 6.4 database

- 原生的操作：

![image-20220413173800756](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413173800756.png)

![image-20220413195013552](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413195013552.png)

![image-20220413195057774](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413195057774.png)

![image-20220413195113814](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413195113814.png)

![image-20220413195222459](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413195222459.png)

- 数据库操作一般新建一个线程，主线程太耗时



- Room Library

![image-20220413195708088](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413195708088.png)

![image-20220413195757247](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413195757247.png)

![image-20220413195818681](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413195818681.png)

![image-20220413200010557](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413200010557.png)

跨进程开发以及解决并发问题：（类似于redis

四大组件之一

![image-20220413200206494](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413200206494.png)

## 7. 安卓多媒体

#### 7.1 图片格式

- jpeg：有损压缩，不支持透明图，手机或者相机拍照的产物

- png：高压缩比的无损压缩，文件大小比jpg大

- WebP：是谷歌提供的一种支持有损压缩和无损压缩的图片文件格式

- 安卓系统特有的一个图片格式：点九图.9.png

  ![image-20220413201228484](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413201228484.png)



#### 7.2 Android Bitmap

![image-20220413202244191](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413202244191.png)

bitmap一般用**ARGB** 8888方式加载图片，也就是说，一个像素点要占4字节。

bitmap加载512*512的图片：

![image-20220413201624588](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413201624588.png)

![image-20220413201642602](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413201642602.png)

可见其内存占用是很大的，为了节约内存资源，可进行缩放：

![image-20220413202523312](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413202523312.png)

设置好bitmap后，通过imageView进行显示：

![image-20220413203334951](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413203334951.png)

![image-20220413203348682](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413203348682.png)

#### 7.3 图片库

![image-20220413203431537](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413203431537.png)

![image-20220413203630656](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413203630656.png)

- .with（context）的context是指activityContext，原因在于可以通过activity的生命周期去监听。当显示图片的activity点击返回onParse不再显示时，Glide会暂停加载图片（意思是暂停从网络上的某个url加载图片，此时图片还没有加载到页面中）。uri是图片url，imageView是指显示到哪个控件上。
- 步骤：

![image-20220413204208636](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413204208636.png)

- playholder：还没加载好的时候显示啥
- error：出错的显示

![image-20220413204449779](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413204449779.png)

![image-20220413204505571](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413204505571.png)

![image-20220413204537162](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413204537162.png)

#### 7.4 视频播放

![image-20220413205403408](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413205403408.png)

## 8. 新技术趋势

### 8.1 kotlin

优势在哪？

- 将语言特性的发展与jvm虚拟机脱钩，kotlin可以在保持jvm1.8前提下不断迭代新的语言特性，跟进现代的编程语言发展之步伐；但java的迭代是与java虚拟机绑定的，要想使用新版本的java语言，就必须升级到新版本的java虚拟机。而java虚拟机的升级对很多项目来说都比较困难。
- 语法更加简洁，避免了传统java代码的冗长
- 谷歌将其定义为安卓开发的第一语言，前景更好

### 8.2 安卓应用架构

#### 8.2.1 MVC（Model-View-Controller）

![image-20220413212901764](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413212901764.png)

- view层：对应于xml布局文件。
- model层：各种java bean，还有一些类似repository类就对应于model层。

![image-20220413231247680](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413231247680.png)

- controller层：就是各种activity

![image-20220413231310139](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413231310139.png)

工作原理：

- 当用户触发事件的时候，view层会发送指令到controller层
- 接着controller去通知model层更新数据
- model层更新完数据以后直接显示在view层上，或者先返回controller，然后由controller更新数据到View上

缺点：

- Android中使用了 Activity 来充当 Controller，但实际上一些 UI 也是由 Activity 来控制的，比如进度条等。因此部分视图就会跟 Controller 捆绑在同一个类了。同时，由于 Activity 的职责过大，Activity 类的代码也会迅速膨胀。
- MVC还有一个重要的缺陷就是 `View` 跟 `Model` 是有交互的，没有做到完全的分离，这就会产生耦合。





#### 8.2.2 MVP（Model）

MVP的model层相对于MVC是一样的，而activity和fragment不再是controller层，而是纯粹的view层，所有关于用户事件的转发全部交由presenter层处理。

![image-20220413214225971](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413214225971.png)

- view层：对应于Activity/Fragment/自定义View，主要负责UI渲染，会绑定到具体的presenter上。

![image-20220413231335360](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413231335360.png)

- model层：数据获取模块。

![image-20220413231426134](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413231426134.png)

- presenter层：负责View和Model之间的交互等，持有Model和View的引用

![image-20220413231417599](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413231417599.png)

实现：定义接口，activity，fragment去实现定义好的接口。在对应的presenter中通过接口调用方法。





#### 8.2.3 MVVM

![image-20220413220006473](C:\Users\picturesque\AppData\Roaming\Typora\typora-user-images\image-20220413220006473.png)

View：发送各种各样的事件

观察者模式：



#### 8.2.4 jetpack

谷歌提供的android框架，重要！

