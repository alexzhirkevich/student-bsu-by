package github.alexzhirkevich.studentbsuby.workers;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = SynchronizationWorker.class
)
public interface SynchronizationWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("github.alexzhirkevich.studentbsuby.workers.SynchronizationWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(
      SynchronizationWorker_AssistedFactory factory);
}
