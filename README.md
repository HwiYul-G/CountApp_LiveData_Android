## LiveData_Android - 이 프로젝트는 무엇인가요?
[Geeks for Geeks site에 있는 Live Data 학습](https://www.geeksforgeeks.org/livedata-in-android-architecture-components/)을 보고 따라한 것입니다. <br>
해당 페이지의 내용을 한국어로 번역하고, 직접 코드를 타이핑하며 Live Data에 대해 배워보고자 합니다.<br>
이 프로젝트에서는 5초만 계산하는 간단한 카운터 앱을 만듭니다.

## [Geeks for Geeks site's live data](https://www.geeksforgeeks.org/livedata-in-android-architecture-components/) - 따라 적으며 학습해보기
LiveData는 안드로이드 아키텍처 구성요소 중 하나이다. Live Data는 <b>관찰 가능한 데이터 홀더 클래스(an observable data holder class)</b>이다. observable이 여기서 의미하는 것은 live data가 Activity나 Fragments같은 UI Controller의 다른 구성요소들에 의해 관찰되는 것을 의미한다. "<i>UI Controller가 live data를 감시한다.</i>"는 것이다. live data의 가장 중요한 것은 activity나 fragment같은 observers(관찰자)의 life cycle을 알고있다는 점이다. 즉, live data는 active life cycle 상태에 있는 Activity, Fragments가은 앱 구성 요소만 업데이트한다. 즉, Live Data는 Started나 Resumed life cycle 상태에 있는 관찰자(Activity, Fragment)에게 알린다. LiveData를 감시하도록 등록된 unactive 관찰자는 변경 사항에 대한 알림을 받지 않는다. unactive 관찰자는 started나 resumed이 아닌 상태를 의미한다. 예제에서 볼 <b>LifeCycle Owner interface를 구현하는 개체와 쌍을 이루는 관찰자를 등록</b>할 수 있다. 이 관계를 통해 해당 Lifecycle 객체의 상태가 destroyed로 변경딜 때 관찰자가 제거될 수 있다.<br>

이 구성요소는 an obserable data holder class이다. 즉, 포함된 값은 관찰 할 수 있다. LiveData는 lifecycle을 인식하는 구성요소이므로 다른 app 구성요소의 lifecycle 상태에 따라 기능을 수행한다. 또한 <b>관찰자의 life cycle 상태가 active인 경우(Started or Resume) LifeData만 앱 구성요소를 업데이트 한다.</b> Live Data는 업데이트를 수행하기 전에 항상 관찰자의 상태를 확인해 관찰자가 이를 수신하기 위해 활성 상태여야 한다. 관찰자의 life cycle이 파괴되면, LiveData는 이를 제거할 수 있으므로 메모리 누수를 방지한다. <b>데이터 동기화 작업이 쉬워진다.</b>
<img src = "https://media.geeksforgeeks.org/wp-content/uploads/20201212151407/LiveDataComponent-660x404.png" width = "500" height="350"/>

<h4>LiveData에서 onActive와 onInactive 메서드를 구현해야 한다.</h4>
<pre>
class LocationLiveData(context : Context) : LiveData<Location>(), AnkoLogger, LocationListener {
  private val locationManager : LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    
    override fun onActive(){
      info("onActive")
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
    }

    override fun onInactive(){
      info("onactive")
      locationManager.removeUpdate(this)
    } 
  }
</pre>

<h4>LiveData 구성요소를 관하기 위해 관찰자(LifecycleOwner, Observer<T>) 메서다가 호출된다.</T></h4>
<pre>
  fun observeLocation() {
    val location = LocationLiveData(this)
    location.observe(this,
      Observe { location ->
        info("location: $location")
      } 
  }
</pre>
