package github.alexzhirkevich.studentbsuby.workers;

import androidx.hilt.work.WorkerAssistedFactory;
import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface SynchronizationWorker_AssistedFactory extends WorkerAssistedFactory<SynchronizationWorker> {
}
