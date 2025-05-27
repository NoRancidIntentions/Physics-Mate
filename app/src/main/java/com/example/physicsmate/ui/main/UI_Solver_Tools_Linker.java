package com.example.physicsmate.ui.main;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.physicsmate.R;
import com.example.physicsmate.activities.math.*;
import com.example.physicsmate.activities.physics.*;
import org.apache.logging.log4j.util.StackLocator;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UI_Solver_Tools_Linker {


    //_________________________________________________________________________________________


    public static class LoggerFix {

        /**
         * {@link StackLocator}
         */
        public static void fix() {
            try {
                //noinspection ResultOfMethodCallIgnored
                StackLocator.getInstance(); // call static init

                Class<StackLocator> stackLocatorClass = StackLocator.class;
                Field get_caller_class_method = stackLocatorClass.getDeclaredField("GET_CALLER_CLASS_METHOD");
                get_caller_class_method.setAccessible(true);
                if (get_caller_class_method.get(null) == null) {
                    Class<LoggerFix> kDebugClass = LoggerFix.class;
                    Method getCallerClassName = kDebugClass.getDeclaredMethod("getCallerClassName", int.class);
                    get_caller_class_method.set(null, getCallerClassName);

                    int magicNumber = 4;
                    Field jdk_7U25_offset = stackLocatorClass.getDeclaredField("JDK_7U25_OFFSET");
                    jdk_7U25_offset.setAccessible(true);
                    jdk_7U25_offset.set(null, magicNumber);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Keep
        @SuppressWarnings("unused")
        @Nullable
        public static Class<?> getCallerClassName(int arg) throws ClassNotFoundException {
            StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
            if (stElements.length > arg) {
                String className = stElements[arg].getClassName();
                return Class.forName(className);
            }
            return null;
        }
    }

    public static class ExtLinks extends Fragment {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

        public ExtLinks() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // TODO: Rename and change types of parameters
        }

        @Override
        public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_ext_links, container, false);
        }

        @SuppressLint("SetJavaScriptEnabled")
        @Override
        public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            Button btnPS, btnGB, btnDC;

            btnDC = requireView().findViewById(R.id.btnDC);
            btnPS = requireView().findViewById(R.id.btnPS);
            btnGB = requireView().findViewById(R.id.btnGB);

            btnDC.setOnClickListener(v -> startActivity(new Intent(getActivity(), DerivativeCalcOnline.class)));
            btnPS.setOnClickListener(v -> startActivity(new Intent(getActivity(), MPLSim.class)));
            btnGB.setOnClickListener(v -> startActivity(new Intent(getActivity(), GeoGebraGraph.class)));

        }


    }

    public static class Solver_frag extends Fragment {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        public Solver_frag() {
            // Required empty public constructor
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                // TODO: Rename and change types of parameters
                getArguments().getString(ARG_PARAM1);
                getArguments().getString(ARG_PARAM2);
            }
        }

        @Override
        public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_solver, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            Button btnGravitation, btnVEAdd, btnVEmultiply, btnKin, btnCollision, btnSpecRelativity;

            btnGravitation = requireView().findViewById(R.id.btnGravitation);
            btnVEAdd = requireView().findViewById(R.id.button12);
            btnVEmultiply = requireView().findViewById(R.id.button13);
            btnKin = requireView().findViewById(R.id.button17);
            btnCollision = requireView().findViewById(R.id.btnCollision);
            btnSpecRelativity = requireView().findViewById(R.id.btnSpecRelativity);

            btnGravitation.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), Gravitation.class)));

            btnVEAdd.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), VEAdd2.class)));

            btnVEmultiply.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), VEMultiply.class)));

            btnKin.setOnClickListener(view2 -> startActivity(new Intent(getActivity(), Kinematics.class)));

            btnCollision.setOnClickListener(view2 -> startActivity(new Intent(getActivity(), CollisionMenu.class)));

            btnSpecRelativity.setOnClickListener(view2 -> startActivity(new Intent(getActivity(), SpecRelativity.class)));
        }
    }

    public static class Tools_frag extends Fragment {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        public Tools_frag() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                // TODO: Rename and change types of parameters
                getArguments().getString(ARG_PARAM1);
                getArguments().getString(ARG_PARAM2);
            }
        }

        @Override
        public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_tools, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            Button btnLOSAC, btnOrthonormalisation, btnVarSolver, btnQE, btnCS, btnExpEval, btnDerivativeCalcOffline, btnIntegralCalcOffline, btnLagrange;

            btnLOSAC = requireView().findViewById(R.id.btnLOSAC);
            btnQE = requireView().findViewById(R.id.button8);
            btnCS = requireView().findViewById(R.id.btnCS);
            btnExpEval = requireView().findViewById(R.id.btnExpEval);
            btnDerivativeCalcOffline = requireView().findViewById(R.id.btnDerivativeCalcOffline);
            btnIntegralCalcOffline = requireView().findViewById(R.id.btnIntegralCalcOffline);
            btnLagrange = requireView().findViewById(R.id.btnLagrange);
            btnVarSolver = requireView().findViewById(R.id.btnVarSolver);
            btnOrthonormalisation = requireView().findViewById(R.id.btnOrthonormalisation);

            btnLOSAC.setOnClickListener(v -> startActivity(new Intent(getActivity(), LOSAC.class)));
            btnQE.setOnClickListener(v -> startActivity(new Intent(getActivity(), QE.class)));
            btnCS.setOnClickListener(v -> startActivity(new Intent(getActivity(), CSMenu.class)));
            btnExpEval.setOnClickListener(v -> startActivity(new Intent(getActivity(), ExpressionEvaluator.class)));
            btnDerivativeCalcOffline.setOnClickListener(v -> startActivity(new Intent(getActivity(), DerivativeCalcOffline2.class)));
            btnIntegralCalcOffline.setOnClickListener(v -> startActivity(new Intent(getActivity(), IntegralCalcOffline.class)));
            btnLagrange.setOnClickListener(v -> startActivity(new Intent(getActivity(), Lagrange_Interpolation.class)));
            btnVarSolver.setOnClickListener(v -> startActivity(new Intent(getActivity(), VarSolver2.class)));
            btnOrthonormalisation.setOnClickListener(v -> startActivity(new Intent(getActivity(), Orthonormalisation.class)));
        }
    }
}