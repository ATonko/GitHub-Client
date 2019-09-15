package tonko.com.client.view.interfaces

interface BaseListView<T> : BaseView
{
    fun isSuccess(list: List<T>)
    fun isEmptyList()
}
