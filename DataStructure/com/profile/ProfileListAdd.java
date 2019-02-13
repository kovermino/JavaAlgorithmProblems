package com.profile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jfree.data.xy.XYSeries;

import com.profile.Profiler.Timeable;

public class ProfileListAdd {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//profileArrayListAddEnd();
		//profileArrayListAddBeginning();
		//profileLinkedListAddBeginning();
		profileLinkedListAddEnd();
	}

	/**
	 * Characterize the run time of adding to the end of an ArrayList
	 */
	public static void profileArrayListAddEnd() {
		Timeable timeable = new Timeable() {
			List<String> list;

			public void setup(int n) {
				list = new ArrayList<String>();
			}

			public void measureTime(int n) {
				for (int i=0; i<n; i++) {
					list.add("a string");
				}
			}
		};
		int EntireLoopSize = 4000;
		int limitEndTime = 1000;
		runProfiler("ArrayList add end", timeable, EntireLoopSize, limitEndTime);
	}
	
	/**
	 * Characterize the run time of adding to the beginning of an ArrayList
	 */
	public static void profileArrayListAddBeginning() {
		Timeable timeable = new Timeable() {
			List<String> list;

			public void setup(int n) {
				list = new ArrayList<String>();
			}

			public void measureTime(int n) {
				for (int i=0; i<n; i++) {
					list.add(0, "a string");
				}
			}
		};
		int EntireLoopSize = 4000;
		int limitEndTime = 1000;
		runProfiler("ArrayList add begin", timeable, EntireLoopSize, limitEndTime);
	}

	/**
	 * Characterize the run time of adding to the beginning of a LinkedList
	 */
	public static void profileLinkedListAddBeginning() {
		Timeable timeable = new Timeable() {
			List<String> list;

			public void setup(int n) {
				list = new LinkedList<String>();
			}

			public void measureTime(int n) {
				for (int i=0; i<n; i++) {
					list.add(0, "a string");
				}
			}
		};
		int EntireLoopSize = 4000;
		int limitEndTime = 1000;
		runProfiler("LinkedList add begin", timeable, EntireLoopSize, limitEndTime);
	}

	/**
	 * Characterize the run time of adding to the end of a LinkedList
	 */
	public static void profileLinkedListAddEnd() {
		Timeable timeable = new Timeable() {
			List<String> list;

			public void setup(int n) {
				list = new LinkedList<String>();
			}

			public void measureTime(int n) {
				for (int i=0; i<n; i++) {
					list.add("a string");
				}
			}
		};
		int EntireLoopSize = 4000;
		int limitEndTime = 1000;
		runProfiler("LinkedList add end", timeable, EntireLoopSize, limitEndTime);
	}

	/**
	 * @param timeable			: 작업을 정의한 인스턴스
	 * @param EntireLoopSize	: 프로파일링 수행 횟수 
	 * @param limitEndTime		: 종료 지정 시간(작업에서 이 시간 이상 소요되면 프로파일링 종료)
	 */
	private static void runProfiler(String title, Timeable timeable, int EntireLoopSize, int limitEndTime) {
		Profiler profiler = new Profiler(title, timeable);
		XYSeries series = profiler.timingLoop(EntireLoopSize, limitEndTime);
		profiler.plotResults(series);
	}
}