--v3
--[[
nezha
1
0
penxue
2
{delay,qianjin,0,0,0,4,0.000000}{default,gongji4,1,0,5,59,1.000000}
1
{default,0.000000,0.000000,480.000000,220.000000,0,2,0,4,0.000000,0,0,0,0}
0

0

2
{1,nezha_texiao,4,5,0.000000,0.000000,54,0}
1
{default,gongji4_texiao2,1,0,0,54,1.000000}
1
{default,480.000000,220.000000,480.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}
0

0

{0,nezha_texiao,2,5,0.000000,0.000000,54,0}
1
{default,gongji4_texiao,1,0,0,54,1.000000}
1
{default,480.000000,220.000000,480.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}
0

0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",0.000000,0,4,0},{"gongji4",1.000000,5,59,1}}, 
pos_sequence      = {{0.000000,0.000000,480.000000,220.000000,0,2,0,4,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"nezha_texiao",4,5,0.000000,0.000000,{{"gongji4_texiao2",1.000000,0,54,1}},{{480.000000,220.000000,480.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0},
{"nezha_texiao",2,5,0.000000,0.000000,{{"gongji4_texiao",1.000000,0,54,1}},{{480.000000,220.000000,480.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0}}, 
bloodNum          = 8,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
