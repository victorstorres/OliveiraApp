package com.example.oliveiraapp.ui.camera

import androidx.camera.core.impl.CameraRepository
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun CameraScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current.applicationContext

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)

        }
    }

    CameraPreview(cameraController = cameraController)



}

@Composable
fun CameraPreview(modifier: Modifier = Modifier, cameraController: LifecycleCameraController) {

    val lifeCycleOwner = LocalLifecycleOwner.current

    AndroidView(
        modifier = modifier,
        factory = { context ->
            PreviewView(context).apply {
                this.controller = cameraController
                cameraController.bindToLifecycle(lifeCycleOwner)
            }
        })

}