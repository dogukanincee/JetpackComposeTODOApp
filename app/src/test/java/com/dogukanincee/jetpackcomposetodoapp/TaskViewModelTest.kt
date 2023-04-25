import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dogukanincee.jetpackcomposetodoapp.TaskViewModel
import com.dogukanincee.jetpackcomposetodoapp.data.TaskRepository
import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task
import io.mockk.coEvery
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TaskViewModelTest {

    // Required to run LiveData-related tests on a background thread
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Required to use coroutines in tests
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = TestCoroutineRule()

    // Create a TestCoroutineDispatcher to use in place of the main dispatcher
    @ExperimentalCoroutinesApi
    private val testDispatcher = coroutineTestRule.testDispatcher

    // Mock objects
    @Mock
    private lateinit var taskRepository: TaskRepository

    @Mock
    private lateinit var observer: Observer<List<Task>>

    // The ViewModel being tested
    private lateinit var viewModel: TaskViewModel

    // Test data
    private val testTask1 = Task(title = "Test Task 1", description = "This is a test task.")
    private val testTask2 = Task(title = "Test Task 2", description = "This is another test task.")

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        // Initialize Mockito annotations
        MockitoAnnotations.initMocks(this)

        // Create the ViewModel instance
        viewModel = TaskViewModel(taskRepository)

        // Set the coroutine dispatcher to the test dispatcher
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun teardown() {
        // Reset the main dispatcher to the default dispatcher
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getAllTasks should return list of tasks`() = coroutineTestRule.runBlockingTest {
        // Create a MutableStateFlow to use as the return value of getAllTasks
        val tasksFlow = MutableStateFlow(listOf(testTask1, testTask2))

        // Mock the taskRepository's getAllTasks function to return tasksFlow
        `when`(taskRepository.getAllTasks()).thenReturn(tasksFlow)

        // Observe the tasks LiveData from the ViewModel
        viewModel.tasks.observeForever(observer)

        // Verify that the observer received the expected list of tasks
        Assert.assertEquals(listOf(testTask1, testTask2), viewModel.tasks.value)

        // Remove the observer to prevent future callbacks
        viewModel.tasks.removeObserver(observer)
    }

    @Test
    fun `addTask should add a new task to the repository`() = coroutineTestRule.runBlockingTest {
        // Call the addTask function with testTask1
        viewModel.addTask(testTask1)

        // Verify that the taskRepository's addTask function was called with testTask1
        verify(taskRepository).addTask(testTask1)
    }

    @Test
    fun `updateTask should update an existing task in the repository`() =
        coroutineTestRule.runBlockingTest {

            // Create a task and add it to the database
            val task = Task(1, "Title", "Description")
            taskRepository.addTask(task)

            // Update the task's title and description
            val updatedTask = task.copy(title = "New Title", description = "New Description")
            viewModel.updateTask(updatedTask)

            // Check that the task was updated in the database
            val tasks = taskRepository.getAllTasks().first()
            assertEquals(1, tasks.size)
            assertEquals(updatedTask, tasks[0])
        }

    @Test
    fun `deleteTask should delete a task from the repository`() =
        coroutineTestRule.runBlockingTest {
            val task = Task(id = 1, title = "Task 1", description = "Description 1")
            coEvery { repository.deleteTask(task) } returns Unit
            viewModel.addTask(task)

            viewModel.deleteTask(task)

            val tasks = viewModel.tasks.first()
            assertEquals(0, tasks.size)
        }

    @Test
    fun `showTaskForm should set the selected task and show the task form`() {
        val task = Task(id = 1, title = "Task 1", description = "Description 1")
        viewModel.showTaskForm(task)

        assertEquals(task, viewModel.selectedTask)
        assertEquals(true, viewModel.showTaskForm)
    }

    @Test
    fun `hideTaskForm should hide the task form`() {
        viewModel.hideTaskForm()

        assertEquals(false, viewModel.showTaskForm)
    }

    @Test
    fun `tasks should emit all tasks from the repository`() = coroutineTestRule.runBlockingTest {
        val tasks = listOf(
            Task(id = 1, title = "Task 1", description = "Description 1"),
            Task(id = 2, title = "Task 2", description = "Description 2"),
            Task(id = 3, title = "Task 3", description = "Description 3"),
        )
        coEvery { repository.getAllTasks() } returns flowOf(tasks)

        val result = viewModel.tasks.first()
        assertEquals(tasks, result)
    }

}