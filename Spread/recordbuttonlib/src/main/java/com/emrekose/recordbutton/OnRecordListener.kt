package com.emrekose.recordbutton

/**
 * Created by emrekose on 18.10.2017.
 */

interface OnRecordListener {
	fun onRecord()
	fun onRecordStart()

	fun onRecordCancel()
	
	fun onRecordFinish()
}
