<template>
  <ModulePreview :key="$route.params.reportId"/>
</template>

<script>
  import ModulePreview from '@/views/report/components/ModulePreview'
	export default {
		name: "index",
    components: { ModulePreview },
    mounted() {

    }
  }
</script>
