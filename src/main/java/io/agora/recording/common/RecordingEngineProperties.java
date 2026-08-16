package io.agora.recording.common;

/**
 * The recording engine properties returned after successfully joining a channel.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 3.0.0
 * @see io.agora.recording.RecordingSDK#getProperties()
 */
public class RecordingEngineProperties{
  /** The relative path of the recorded files and recording log. */
  private String storageDir;

  /**
   * Get the relative path of the recorded files and recording log.
   * @return The relative path of the recorded files and recording log.
   */
  public String getStorageDir(){
    return storageDir;
  }

}
