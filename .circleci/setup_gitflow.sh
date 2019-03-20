export ENV_SUFFIX=`\cat .circleci/branches.tsv | grep $CIRCLE_BRANCH | awk '{print $1}'`
export ENV_REGION=`\cat .circleci/branches.tsv | grep $CIRCLE_BRANCH | awk '{print $3}'`
