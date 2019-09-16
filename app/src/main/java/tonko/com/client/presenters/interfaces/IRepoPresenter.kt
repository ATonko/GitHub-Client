package tonko.com.client.presenters.interfaces

interface IRepoPresenter
{

    fun getCommits(user: String,
                   project: String)
}
