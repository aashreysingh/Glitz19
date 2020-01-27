<?xml version="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:fitsSystemWindows="true"
    android:id="@+id/drawer_layout"
    android:layout_height="match_parent"
    tools:context=".RegisterNow">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <include
            android:id="@+id/include"
            layout="@layout/toolbar"
            android:layout_width="match_parent"
            android:layout_height="56dp"
            app:layout_constraintTop_toTopOf="parent">
        </include>

        <ImageView
            android:contentDescription="@string/close"
            android:id="@+id/close"
            android:layout_width="18dp"
            android:layout_height="18dp"
            android:layout_marginEnd="16dp"
            android:scaleType="centerInside"
            android:src="@drawable/close"
            app:layout_constraintBottom_toBottomOf="@+id/include"
            app:layout_constraintEnd_toEndOf="@+id/include"
            app:layout_constraintTop_toTopOf="@+id/include" />

        <ProgressBar
            android:id="@+id/progressBar2"
            style=""
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:indeterminate="true"
            android:indeterminateTint="@color/ReminderFAB"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="1.0"
            app:layout_constraintStart_toStartOf="parent"
            tools:layout_editor_absoluteY="56dp" />

        <WebView
            android:id="@+id/webView"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_marginTop="4dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toTopOf="@+id/progressBar2"
            tools:layout_editor_absolu