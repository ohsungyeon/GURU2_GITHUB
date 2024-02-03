package com.example.guru_start

import android.os.Parcel
import android.os.Parcelable

class Lecture(
    var 학기: String? = null,
    var 교과목번호: String? = null,
    var 강의명: String? = null,
    var 분반: String? = null,
    var 교수명: String? = null,
    var 구분: String? = null,
    var 학년: String? = null,
    var 강의시간: String? = null,
    var 장소: String? = null,
    var 학점: String? = null,
    var 수강대상: String? = null,
    var 교재: String? = null,
    var 사용언어: String? = null,
    var 별점: String? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // 각 속성을 쓰는 순서대로 writeToParcel에 추가
        parcel.writeString(학기)
        parcel.writeString(교과목번호)
        parcel.writeString(강의명)
        parcel.writeString(분반)
        parcel.writeString(교수명)
        parcel.writeString(구분)
        parcel.writeString(학년)
        parcel.writeString(강의시간)
        parcel.writeString(장소)
        parcel.writeString(학점)
        parcel.writeString(수강대상)
        parcel.writeString(교재)
        parcel.writeString(사용언어)
        parcel.writeString(별점)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lecture> {
        override fun createFromParcel(parcel: Parcel): Lecture {
            return Lecture(parcel)
        }

        override fun newArray(size: Int): Array<Lecture?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return """
            학기: $학기
            교과목번호: $교과목번호
            강의명: $강의명
            분반: $분반
            교수명: $교수명
            구분: $구분
            학년: $학년
            강의시간: $강의시간
            장소: $장소
            학점: $학점
            수강대상: $수강대상
            교재: $교재
            사용언어: $사용언어
            별점: $별점
        """.trimIndent()
    }
}
