//import com.mongodb.client.MongoCollection;
//import mapper.ActivityMapper;
//import model.Activity;
//import org.bson.Document;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import repository.ActivityRepository;
//import repository.ActivityRepositoryImpl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("Group of units tests for the activity controller")
//public class ActivityControllerImplTest {
//
//    @Mock
//    Activity activity = new Activity(
//            "Tennis",
//            2,
//            new Date(2023, 06, 15),
//            302,
//            24
//    );
//
//    String id = "Success !";
//
//    ActivityRepository activityRepository;
//    ActivityRepositoryImpl classUnderTest;
//
//    //@Disabled
//    //@BeforeEach
//    //public void setUp(){
//    //    classUnderTest = new ActivityRepositoryImpl(activity);
//    //}
//
//    @Test
//    @DisplayName("Test if the save method of the repository is called with an Activity")
//    public void save_withActivity_shouldCallRepository () {
//        //Arrange
//        when(activityRepository.save(activity)).thenReturn(id);
//
//        //Act
//        String result = classUnderTest.save(activity);
//
//        //Assert
//        verify(activityRepository).save(activity);
//        verify(activityRepository).save(any(Activity.class));
//        verify(activityRepository, times(1)).save(any(Activity.class));
//        assertThat(result).isEqualTo(id);
//    }
//
//    @Nested
//    class getTest {
//        @Test
//        @DisplayName("Test if get methode of the repository is called")
//        public void getAll_shouldCallRepository(){
//            List<Activity> activityList = new ArrayList<>();
//            activityList.add(activity);
//            when(activityRepository.getAll()).thenReturn(activityList);
//
//            List result = classUnderTest.getAll();
//
//            verify(activityRepository).getAll();
//            verify(activityRepository, times(1)).getAll();
//            verify(activityRepository, never()).save(any(Activity.class));
//        }
//
//        @Test
//        @DisplayName("Test if the get methode return a list of activity")
//        public void getAll_shouldReturnActivityList() {
//            List<Activity> activityList = new ArrayList<>();
//            activityList.add(activity);
//            when(activityRepository.getAll()).thenReturn(activityList);
//
//            List result = classUnderTest.getAll();
//
//            assertThat(result).isEqualTo(activityList);
//        }
//
//    }
//
//}
