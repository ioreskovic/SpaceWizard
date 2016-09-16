package org.lopina.sort.impl;

import org.lopina.sort.Sort;

import java.util.*;

public class ShellSort<T> extends Sort<T> {

    private static final StepSelectionStrategy.Builder DEFAULT = StepStrategyBuilders.getFor(StepStrategy.INSERTION);

    private final StepSelectionStrategy.Builder stepSelectionStrategyBuilder;

    public ShellSort() {
        this.stepSelectionStrategyBuilder = DEFAULT;
    }

    public ShellSort(StepStrategy stepStrategy) {
        this.stepSelectionStrategyBuilder = StepStrategyBuilders.getFor(stepStrategy);
    }

    @Override
    public T[] sort(T[] input, Comparator<? super T> comparator) {
        int n = input.length;
        StepSelectionStrategy stepSelectionStrategy = stepSelectionStrategyBuilder.fromCollectionSize(n).build();

        int step = stepSelectionStrategy.nextStep();

        while (step >= 1) {
            for (int i = step; i < n; i++) {
                for (int j = i; j >= step; j -= step) {
                    T left = input[j - step];
                    T right = input[j];

                    if (less(right, left, comparator)) {
                        swap(input, j - step, j);
                    } else {
                        break;
                    }
                }
            }

            step = stepSelectionStrategy.nextStep();
        }

        return input;
    }

    @Override
    public List<T> sort(List<T> input, Comparator<? super T> comparator) {
        int n = input.size();
        StepSelectionStrategy stepSelectionStrategy = stepSelectionStrategyBuilder.fromCollectionSize(n).build();

        int step = stepSelectionStrategy.nextStep();

        while (step >= 1) {
            for (int i = step; i < n; i++) {
                for (int j = i; j >= step; j -= step) {
                    T left = input.get(j - step);
                    T right = input.get(j);

                    if (less(right, left, comparator)) {
                        swap(input, j - step, j);
                    } else {
                        break;
                    }
                }
            }

            step = stepSelectionStrategy.nextStep();
        }

        return input;
    }

    private static class StepStrategyBuilders {
        private StepStrategyBuilders() {
        }

        private static StepSelectionStrategy.Builder getFor(StepStrategy strategy) {
            return buildersMap.get(strategy);
        }

        private static final Map<StepStrategy, StepSelectionStrategy.Builder> buildersMap;

        static {
            buildersMap = new HashMap<StepStrategy, StepSelectionStrategy.Builder>();

            buildersMap.put(null, new SedgewickStepSelectionStrategy.Builder(1));
            buildersMap.put(StepStrategy.SEDGEWICK, new SedgewickStepSelectionStrategy.Builder(1));
            buildersMap.put(StepStrategy.INSERTION, new InsertionStepSelectionStrategy.Builder(1));
        }
    }

    public enum StepStrategy {
        SEDGEWICK,
        INSERTION
    }

    private abstract static class StepSelectionStrategy {
        protected final int collectionSize;
        private final Iterator<Integer> steps;

        private StepSelectionStrategy(int collectionSize) {
            this.collectionSize = collectionSize;
            this.steps = initSteps();
        }

        private Iterator<Integer> initSteps() {
            Iterator<Integer> iterator = initStepsInternal();
            if (iterator == null) {
                iterator = Collections.emptyIterator();
            }

            return iterator;
        }

        protected abstract Iterator<Integer> initStepsInternal();

        public int nextStep() {
            if (this.steps.hasNext()) {
                return this.steps.next();
            }

            return 0;
        }

        private abstract static class Builder {
            protected final int collectionSize;

            private Builder(int collectionSize) {
                this.collectionSize = collectionSize;
            }

            Builder fromCollectionSize(int collectionSize) {
                return newBuilder(collectionSize);
            }

            protected abstract Builder newBuilder(int collectionSize);
            abstract StepSelectionStrategy build();
        }
    }

    private static final class SedgewickStepSelectionStrategy extends StepSelectionStrategy {

        private static final int[] steps = new int[]{
                1391376, 463792, 198768, 86961, 33936,
                13776, 4592, 1968, 861, 336,
                112, 48, 21, 7, 3, 1
        };

        private SedgewickStepSelectionStrategy(int collectionSize) {
            super(collectionSize);
        }

        @Override
        protected Iterator<Integer> initStepsInternal() {
            List<Integer> stepList = new ArrayList<Integer>();
            for (int i = 0; i < steps.length; i++) {
                if (steps[i] < collectionSize) {
                    stepList.add(steps[i]);
                }
            }

            return stepList.iterator();
        }

        private static final class Builder extends StepSelectionStrategy.Builder {

            private Builder(int collectionSize) {
                super(collectionSize);
            }

            @Override
            protected StepSelectionStrategy.Builder newBuilder(int collectionSize) {
                return new Builder(collectionSize);
            }

            @Override
            StepSelectionStrategy build() {
                return new SedgewickStepSelectionStrategy(collectionSize);
            }
        }
    }

    private static final class InsertionStepSelectionStrategy extends StepSelectionStrategy {

        private InsertionStepSelectionStrategy(int collectionSize) {
            super(collectionSize);
        }

        @Override
        protected Iterator<Integer> initStepsInternal() {
            List<Integer> stepList = new ArrayList<Integer>();

            stepList.add(1);

            return stepList.iterator();
        }

        private static final class Builder extends StepSelectionStrategy.Builder {

            private Builder(int collectionSize) {
                super(collectionSize);
            }

            @Override
            protected StepSelectionStrategy.Builder newBuilder(int collectionSize) {
                return new Builder(collectionSize);
            }

            @Override
            StepSelectionStrategy build() {
                return new InsertionStepSelectionStrategy(collectionSize);
            }
        }
    }
}
